package com.iot.travel.service;

import com.iot.travel.entity.Planner;
import com.iot.travel.entity.User;
import com.iot.travel.dto.PlannerDTO;

public interface PlannerService {

    // 플래너 등록 서비스
    Long register(PlannerDTO dto);

    // 플래너 목록화 서비스
    PlannerDTO get(Long pno);

    default Planner dtoToEntity(PlannerDTO dto) {
        User user = User.builder().uno(dto.getPuno()).build();

        Planner planner = Planner.builder()
                .pno(dto.getPno())
                .puno(user)
                .pCount(dto.getPCount())
                .pDelete(dto.getPDelete())
                .pStart(dto.getPStart())
                .pEnd(dto.getPEnd())
                .build();
        return planner;
    }

    default PlannerDTO entityToDTO(Planner planner, User user) {

        PlannerDTO plannerDTO = PlannerDTO.builder()
                .pno(planner.getPno())
                .puno(user.getUno())
                .nickname(user.getNickname())
                .pCount(planner.getPCount())
                .pDelete(planner.getPDelete())
                .pStart(planner.getPStart())
                .pEnd(planner.getPEnd())
                .build();
        return plannerDTO;
    }
}
