package com.iot.travel.service;

import com.iot.travel.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import com.iot.travel.dto.PlannerLocDTO;
import com.iot.travel.repository.PlannerLocRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class PlannerLocServiceImpl implements  PlannerLocService{

    private final PlannerLocRepository repository;

    // 플래너 세부 정보 등록 서비스
    @Override
    public Long register(PlannerLocDTO dto) {
        PlannerLoc plannerLoc = dtoToEntity(dto);
        repository.save(plannerLoc);
        return plannerLoc.getPlno();
    }

}
