package com.iot.travel.repository;

import com.iot.travel.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import com.iot.travel.entity.Reply;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    //게시글 삭제 시 댓글들 1로 삭제 처리
    @Modifying
    @Query("update Reply r set r.rdelete = 1 where r.rbno.bno =:bno ")
    void deleteByBno(Long bno);

    @Query("select r from Reply r where r.rbno = :rbno and r.rdelete = 0 order by r.rno")
    List<Reply> getReplyByRbnoOrderByRno(Board rbno);
}
