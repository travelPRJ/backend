package com.iot.travel.controller;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.iot.travel.dto.PlannerLocDTO;
import com.iot.travel.service.PlannerLocService;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/plannerloc/")
@Log4j2
@RequiredArgsConstructor
public class PlannerLocController {

    private final PlannerLocService plannerLocService;

    @GetMapping(value ="/planner/{ppno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlannerLocDTO>> getListByPlanner(@PathVariable("ppno") Long ppno) {
        log.info("ppno : " + ppno);
        List<PlannerLocDTO> plannerLocDTOList = plannerLocService.getList(ppno);
        return new ResponseEntity<>(plannerLocService.getList(ppno), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<List<Long>> register(@RequestBody List<PlannerLocDTO> plannerLocDTOList) {
        List<Long> plnoList = new ArrayList<>();
        for (PlannerLocDTO plannerLocDTO : plannerLocDTOList) {
            Long plno = plannerLocService.register(plannerLocDTO);
            plnoList.add(plno);
        }
        return new ResponseEntity<>(plnoList, HttpStatus.OK);
    }

    @PostMapping("/modify/{ppno}")
    public ResponseEntity<String> modify(@PathVariable Long ppno, @RequestBody List<PlannerLocDTO> plannerLocDTOList) {
        plannerLocService.modify(plannerLocDTOList, ppno);
        return new ResponseEntity<>("PlannerLoc updated successfully", HttpStatus.OK);
    }
}
