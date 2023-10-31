package com.iot.travel.service;

import com.iot.travel.dto.PageRequestDTO;
import com.iot.travel.dto.PageResultDTO;
import com.iot.travel.entity.Planner;
import com.iot.travel.entity.User;
import com.iot.travel.dto.PlannerDTO;

public interface PlannerService {

    // 플래너 목록 처리
    PageResultDTO<PlannerDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    // 플래너 등록 서비스
    Long register(PlannerDTO dto);

    // 플래너 조회 서비스
    PlannerDTO get(Long pno);

    // 플래너 삭제 서비스
    void remove(Long pno, PlannerDTO dto);

    // 플래너 수정 서비스
    void modify(PlannerDTO plannerDTO);

    default Planner dtoToEntity(PlannerDTO dto) {
        User user = User.builder().uno(dto.getPuno()).build();

        Planner planner = Planner.builder()
                .pno(dto.getPno())
                .puno(user)
                .ptitle(dto.getPtitle())
                .pcount(dto.getPcount())
                .pdelete(dto.getPdelete())
                .pstart(dto.getPstart())
                .pend(dto.getPend())
                .build();
        return planner;
    }

    default PlannerDTO entityToDTO(Planner planner, User user) {

        PlannerDTO plannerDTO = PlannerDTO.builder()
                .pno(planner.getPno())
                .puno(user.getUno())
                .ptitle(planner.getPtitle())
                .nickname(user.getNickname())
                .pcount(planner.getPcount())
                .pdelete(planner.getPdelete())
                .pstart(planner.getPstart())
                .pend(planner.getPend())
                .build();
        return plannerDTO;
    }
}
