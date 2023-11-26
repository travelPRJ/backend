package com.iot.travel.service;

import com.iot.travel.dto.BoardDTO;
import com.iot.travel.entity.User;
import com.iot.travel.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.iot.travel.dto.ReplyDTO;
import com.iot.travel.entity.Board;
import com.iot.travel.entity.Reply;
import com.iot.travel.repository.ReplyRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    // 댓글 등록
    @Override
    public Long register(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        Board board = boardRepository.findById(replyDTO.getRbno())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. bno=" + replyDTO.getRbno()));
        reply.setRbno(boardRepository.save(board));
        replyRepository.save(reply);
        return reply.getRno();
    }

    // 댓글 목록
    @Override
    public List<ReplyDTO> getList(Long bno) {
        List<Reply> result = replyRepository.getReplyByRbnoOrderByRno(Board.builder().bno(bno).build());

        return result.stream().map(reply -> {
            User user = reply.getRuno();
            return entityToDTO(reply, user);
        }).collect(Collectors.toList());
    }

    // 댓글 수정
    @Transactional
    @Override
    public void modify(ReplyDTO replyDTO) {
        Reply reply = replyRepository.getReferenceById(replyDTO.getRno());

        reply.changeContent(replyDTO.getRcontent());

        replyRepository.save(reply);
    }

    // 댓글 삭제
    @Transactional
    @Override
    public void remove(ReplyDTO replyDTO) {
        Reply reply  =replyRepository.getReferenceById(replyDTO.getRno());

        if(replyDTO.getRdelete() == 0) {
            reply.changeDelete(1);
            replyRepository.save(reply);
        }
    }
}
