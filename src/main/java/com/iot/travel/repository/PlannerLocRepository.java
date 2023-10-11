package com.iot.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.iot.travel.entity.PlannerLoc;
public interface PlannerLocRepository extends JpaRepository<PlannerLoc, Long> {
}
