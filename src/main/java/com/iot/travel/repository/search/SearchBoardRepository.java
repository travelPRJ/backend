package com.iot.travel.repository.search;

import com.iot.travel.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface SearchBoardRepository {

    Board search1();

    @Query(value = "select b, u, count(r) " +
            "from Board b " +
            "left join b.buno u " +
            "left join Reply r on r.rbno = b " +
            "where b.bdelete <> 1 " +
            "group by b",
            countQuery = "select count(b) from Board b")
    Page<Object[]> searchPage(String type, Pageable pageable);
}
