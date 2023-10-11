package com.iot.travel.service;

import com.iot.travel.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import com.iot.travel.dto.PlannerDTO;
import com.iot.travel.entity.Planner;
import com.iot.travel.repository.PlannerRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class PlannerServiceImpl implements PlannerService {
    private final PlannerRepository repository;

    @Override
    public Long register(PlannerDTO dto) {
        Planner planner = dtoToEntity(dto);
        repository.save(planner);
        return planner.getPno();
    }

    @Override
    public PlannerDTO get(Long pno) {
        Object result = repository.getPlannerByPno(pno);
        Object[] arr = (Object[])result;
        return entityToDTO((Planner)arr[0], (User)arr[1]);
    }
}
