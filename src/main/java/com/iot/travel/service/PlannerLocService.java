package com.iot.travel.service;

import com.iot.travel.dto.PlannerDTO;
import com.iot.travel.entity.Planner;
import com.iot.travel.dto.PlannerLocDTO;
import com.iot.travel.entity.PlannerLoc;

import java.util.List;

public interface PlannerLocService {

    // 플래너 세부 데이터 등록 서비스
    Long register(PlannerLocDTO dto);

    List<PlannerLocDTO> getList(Long plno);

    void modify(PlannerLocDTO dto);

    default PlannerLoc dtoToEntity(PlannerLocDTO dto) {
        Planner planner = Planner.builder().pno(dto.getPpno()).build();

        PlannerLoc plannerLoc = PlannerLoc.builder()
                .plno(dto.getPlno())
                .ppno(planner)
                .placeName(dto.getPlaceName())
                .transport(dto.getTransport())
                .region(dto.getRegion())
                .lstart(dto.getLstart())
                .lend(dto.getLend())
                .ldelete(dto.getLdelete())
                .lat(dto.getLat())
                .lng(dto.getLng())
                .build();
        return plannerLoc;
    }

    default PlannerLocDTO entityToDTO(PlannerLoc plannerLoc) {

        PlannerLocDTO plannerLocDTO = PlannerLocDTO.builder()
                .plno(plannerLoc.getPlno())
                .placeName(plannerLoc.getPlaceName())
                .transport(plannerLoc.getTransport())
                .region(plannerLoc.getRegion())
                .lstart(plannerLoc.getLstart())
                .lend(plannerLoc.getLend())
                .ldelete(plannerLoc.getLdelete())
                .lat(plannerLoc.getLat())
                .lng(plannerLoc.getLng())
                .build();
        return plannerLocDTO;
    }
}
