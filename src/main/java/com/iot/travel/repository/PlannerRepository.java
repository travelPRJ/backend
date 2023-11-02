package com.iot.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.iot.travel.entity.Planner;

import java.util.List;

public interface PlannerRepository extends JpaRepository<Planner, Long> {
    @Query("select p, u from Planner p left join p.puno u where p.pno =:pno")
    Object getPlannerWithUser(@Param("pno") Long pno);

    @Query("select p, pl from Planner p left join PlannerLoc pl on pl.ppno = p where p.pno = :pno")
    List<Object[]> getPlannerWithPlannerLoc(@Param("pno") Long pno);

    @Query(value = "select p, u " +
            "from Planner p " +
            "left join p.puno u " +
            "left join PlannerLoc pl on pl.ppno = p " +
            "where p.pdelete <> 1 " +
            "group by p",
            countQuery = "select count(p) from Planner p")
    Page<Object[]> getPlannerWithPlannerLoc(Pageable pageable);


    @Query("select p, u from Planner p left join p.puno u where p.pno = :pno")
    Object getPlannerByPno(@Param("pno") Long pno);
}
