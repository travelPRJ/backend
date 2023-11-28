package com.iot.travel.repository.search;

import com.iot.travel.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface SearchBoardRepository {

    Board search1();

    Page<Object[]> searchPage(String type, Pageable pageable);
}
