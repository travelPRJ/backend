package com.iot.travel.service;

import com.iot.travel.dto.PageRequestDTO;
import com.iot.travel.dto.PageResultDTO;
import com.iot.travel.entity.User;
import com.iot.travel.repository.PlannerLocRepository;
import com.iot.travel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.iot.travel.dto.PlannerDTO;
import com.iot.travel.entity.Planner;
import com.iot.travel.repository.PlannerRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class PlannerServiceImpl implements PlannerService {
    private final PlannerRepository repository;
    private final PlannerLocRepository plannerLocRepository;

    // 플래너 등록 서비스
    @Override
    public Long register(PlannerDTO dto) {
        Planner planner = dtoToEntity(dto);
        repository.save(planner);
        return planner.getPno();
    }

    // 플래너 목록 서비스
    @Override
    public PageResultDTO<PlannerDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], PlannerDTO> fn = (en -> entityToDTO((Planner) en[0], (User)en[1]));
        Page<Object[]> result = repository.getPlannerWithPlannerLoc(pageRequestDTO.getPageable(Sort.by("pno").descending()));

        return new PageResultDTO<>(result, fn);
    }

    // 플래너 조회 서비스
    @Override
    public PlannerDTO get(Long pno) {
        Object result = repository.getPlannerByPno(pno);
        Object[] arr = (Object[])result;
        return entityToDTO((Planner)arr[0], (User)arr[1]);
    }

    // 플래너 삭제 서비스
    @Transactional
    @Override
    public void remove(PlannerDTO plannerDTO) {
        Planner planner = repository.getReferenceById(plannerDTO.getPno());

            // 삭제 여부(pdelete)를 업데이트하는 부분
        if (plannerDTO.getPdelete() == 0) {
                planner.changeDelete(1);
        }

        repository.save(planner);

    }

    // 플래너 수정 서비스
    @Override
    public void modify(PlannerDTO plannerDTO) {
        Planner planner = repository.getReferenceById(plannerDTO.getPno());

        if(plannerDTO.getPtitle() != null) {
            planner.changeTitle(plannerDTO.getPtitle());
        }
        if(plannerDTO.getPstart() != null) {
            planner.changeStartDate(plannerDTO.getPstart());
        }
        if(plannerDTO.getPend() != null) {
            planner.changeEndDate(plannerDTO.getPend());
        }
        repository.save(planner);
    }
}
