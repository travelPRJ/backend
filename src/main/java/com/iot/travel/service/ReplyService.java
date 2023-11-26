package com.iot.travel.service;

import com.iot.travel.dto.ReplyDTO;
import com.iot.travel.entity.Board;
import com.iot.travel.entity.User;
import com.iot.travel.entity.Reply;
import java.util.List;

public interface ReplyService {
    Long register(ReplyDTO replyDTO);
    List<ReplyDTO> getList(Long bno);
    void modify(ReplyDTO replyDTO);
    void remove(ReplyDTO replyDTO);

    default Reply dtoToEntity(ReplyDTO replyDTO) {
        Board board = Board.builder().bno(replyDTO.getRbno()).build();
        User user = User.builder().uno(replyDTO.getRuno()).build();

        Reply reply = Reply.builder()
                .rno(replyDTO.getRno())
                .rcontent(replyDTO.getRcontent())
                .runo(user)
                .rbno(board)
                .rdelete(replyDTO.getRdelete())
                .build();
        return reply;
    }

    default ReplyDTO entityToDTO(Reply reply, User user) {
        ReplyDTO dto = ReplyDTO.builder()
                .rno(reply.getRno())
                .rcontent(reply.getRcontent())
                .rdelete(reply.getRdelete())
                .rnickname(user.getNickname())
                .runo(reply.getRuno().getUno())
                .rbno(reply.getRbno().getBno())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();
        return dto;
    }
}
