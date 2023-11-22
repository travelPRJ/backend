package com.iot.travel.repository;

import com.iot.travel.repository.search.SearchBoardRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.iot.travel.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository {

    @Query("select b, u from Board b left join b.buno u  where b.bno =:bno")
    Object getBoardWithUser(@Param("bno") Long bno);

    @Query("select b, r from Board b left join Reply r on r.rbno = b where b.bno = :bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

    @Query(value = "select b, u, count(r) " +
            "from Board b " +
            "left join b.buno u " +
            "left join Reply r on r.rbno = b " +
            "where b.bdelete <> 1 " +
            "group by b",
            countQuery = "select count(b) from Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    @Query("select b, u, count(r) " +
            "from Board b left join b.buno u " +
            "left outer join Reply r on r.rbno = b " +
            "where b.bno = :bno")
    Object getBoardByBno(@Param("bno") Long bno);

}
