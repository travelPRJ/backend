package com.iot.travel.controller;

import com.iot.travel.dto.*;
import com.iot.travel.service.PlannerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/planner")
@Log4j2
@RequiredArgsConstructor
public class PlannerController {

    private final PlannerService plannerService;

    @GetMapping("/list")
    public ResponseEntity<PageResultDTO<PlannerDTO, Object[]>> list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list..................." + pageRequestDTO);
        model.addAttribute("result", plannerService.getList(pageRequestDTO));

        return new ResponseEntity<>(plannerService.getList(pageRequestDTO), HttpStatus.OK);
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
    public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long pno, Model model) {
        log.info("pno : " + pno);
        PlannerDTO plannerDTO = plannerService.get(pno);
        log.info(plannerDTO);
        model.addAttribute("dto", plannerDTO);
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("pno") Long pno, @ModelAttribute PlannerDTO plannerDTO) {

        plannerService.remove(pno, plannerDTO);

        return "redirect:/planner/list";
    }

    @PostMapping("/modify")
    public String modify(PlannerDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
        log.info("post modify..................................");
        log.info("dto : "+ dto);

        plannerService.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());

        return "redirect:/planner/read";
    }

}
