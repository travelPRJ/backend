package com.iot.travel.repository;

import com.iot.travel.entity.Planner;
import com.iot.travel.entity.PlannerLoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlannerLocRepository extends JpaRepository<PlannerLoc, Long> {

    // 플래너 삭제 시 내용들 1로 처리
    @Modifying
    @Query("update PlannerLoc pl set pl.ldelete = 1 where pl.ppno.pno =:pno ")
    void deleteByPno(Long pno);

    List<PlannerLoc> getPlannerLocByPpnoOrderByPlno(Planner ppno);
}

