package com.iot.travel.service;

import com.iot.travel.dto.PageRequestDTO;
import com.iot.travel.dto.PageResultDTO;
import com.iot.travel.entity.User;
import com.iot.travel.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.iot.travel.dto.BoardDTO;
import com.iot.travel.entity.Board;
import com.iot.travel.repository.ReplyRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    // 리뷰 등록 서비스
    @Override
    public Long register(BoardDTO boardDTO) {
        Board board = dtoToEntity(boardDTO);
        boardRepository.save(board);
        return board.getBno();
    }

    // 리뷰 목록 서비스
    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board) en[0], (User) en[1], (Long) en[2]));
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));

        return new PageResultDTO<>(result, fn);
    }

    // 리뷰 조회 서비스
    @Override
    public BoardDTO get(Long bno) {
        Object result = boardRepository.getBoardByBno(bno);
        Object[] arr = (Object[])result;
        return entityToDTO((Board)arr[0], (User)arr[1], (Long)arr[2]);
    }

    // 리뷰 삭제 서비스
    @Transactional
    @Override
    public void remove(BoardDTO boardDTO) {
        Board board = boardRepository.getReferenceById(boardDTO.getBno());

        if(boardDTO.getBdelete() == 0) {
            board.changeDelete(1);
            replyRepository.deleteByBno(boardDTO.getBno());
            boardRepository.save(board);
        }
    }

    // 리뷰 수정 서비스
    @Transactional
    @Override
    public void modify(BoardDTO dto) {
        Board board = boardRepository.getReferenceById(dto.getBno());

        if(dto.getBtitle() != null) {
            board.changeTitle(dto.getBtitle());
        }
        if(dto.getBcontent() != null) {
            board.changeContent(dto.getBcontent());
        }
        boardRepository.save(board);
    }
}
