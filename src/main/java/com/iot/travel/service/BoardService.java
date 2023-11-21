package com.iot.travel.service;

import com.iot.travel.dto.PageRequestDTO;
import com.iot.travel.dto.PageResultDTO;
import com.iot.travel.dto.PlannerDTO;
import com.iot.travel.entity.Board;
import com.iot.travel.entity.User;
import com.iot.travel.dto.BoardDTO;

public interface BoardService {

    // 리뷰 등록 서비스
    Long register(BoardDTO dto);

    // 리뷰 목록 처리
    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    // 리뷰 조회 처리
    BoardDTO get(Long bno);

    // 리뷰 조회 처리
    void remove(BoardDTO dto);

    // 리뷰 수정 서비스
    void modify(BoardDTO dto);

    default Board dtoToEntity(BoardDTO dto) {
        User user = User.builder().uno(dto.getBuno()).build();

        Board board = Board.builder()
                .bno(dto.getBno())
                .buno(user)
                .btitle(dto.getBtitle())
                .bcontent(dto.getBcontent())
                .bdelete(dto.getBdelete())
                .build();
        return board;
    }

    default BoardDTO entityToDTO(Board board, User user, Long replyCount) {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .buno(user.getUno())
                .btitle(board.getBtitle())
                .bcontent(board.getBcontent())
                .bdelete(board.getBdelete())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .replyCount(replyCount.intValue())
                .build();
        return boardDTO;
    }
}
