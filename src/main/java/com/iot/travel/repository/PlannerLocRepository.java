package com.iot.travel.repository;

import com.iot.travel.entity.PlannerLoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PlannerLocRepository extends JpaRepository<PlannerLoc, Long> {

    @Modifying
    @Query("update PlannerLoc pl set pl.ldelete = 1 where pl.ppno.pno =:pno ")
    void deleteByPno(Long pno);

}
