package com.iot.travel.service;

import com.iot.travel.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import com.iot.travel.dto.PlannerLocDTO;
import com.iot.travel.repository.PlannerLocRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<PlannerLocDTO> getList(Long plno) {
        List<PlannerLoc> result = repository.getPlannerLocByPpnoOrderByPlno(Planner.builder().pno(plno).build());

        return result.stream().map(plannerLoc -> entityToDTO(plannerLoc)).collect(Collectors.toList());
    }

    @Override
    public void modify(PlannerLocDTO dto) {
        PlannerLoc plannerLoc = dtoToEntity(dto);
        repository.save(plannerLoc);
    }

}
