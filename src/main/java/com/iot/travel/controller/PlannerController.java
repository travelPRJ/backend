package com.iot.travel.controller;

import com.iot.travel.dto.*;
import com.iot.travel.entity.Planner;
import com.iot.travel.service.PlannerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/planner")
@Log4j2
@RequiredArgsConstructor
public class PlannerController {

    private final PlannerService plannerService;

    @GetMapping("/list")
    public ResponseEntity<PageResultDTO<PlannerDTO, Object[]>> list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list..................." + pageRequestDTO);
        model.addAttribute("result", plannerService.getList(pageRequestDTO));
        PageResultDTO<PlannerDTO, Object[]> plannerList = plannerService.getList(pageRequestDTO);
        return new ResponseEntity<>(plannerList, HttpStatus.OK);
    }

    @GetMapping("/register")
    public void register() {
        log.info("register get....");
    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody PlannerDTO dto) {
        Long pno = plannerService.register(dto);
        return ResponseEntity.ok().body(pno);
    }

    @GetMapping({"/read", "/modify"})
    public ResponseEntity<PlannerDTO> read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long pno, Model model) {
        log.info("pno : " + pno);
        PlannerDTO plannerDTO = plannerService.get(pno);
        log.info(plannerDTO);
        model.addAttribute("dto", plannerDTO);

        return new ResponseEntity<>(plannerService.get(pno), HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<Long> remove(@RequestBody PlannerDTO dto) {

        plannerService.remove(dto);
        return ResponseEntity.ok(dto.getPno());
    }


    @PostMapping("/modify")
    public ResponseEntity<Long> modify(@RequestBody PlannerDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {

        log.info("post modify..................................");
        log.info("dto : "+ dto);

        plannerService.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());

        redirectAttributes.addAttribute("pno", dto.getPno());
        return ResponseEntity.ok(dto.getPno());
    }

}
