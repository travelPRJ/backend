package com.iot.travel.controller;

import com.iot.travel.dto.*;
import com.iot.travel.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public ResponseEntity<PageResultDTO<BoardDTO, Object[]>> list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list..................." + pageRequestDTO);
        model.addAttribute("result", boardService.getList(pageRequestDTO));

        return new ResponseEntity<>(boardService.getList(pageRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/register")
    public void register() {
        log.info("register get....");
    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody BoardDTO boardDTO) {
        Long bno = boardService.register(boardDTO);
        return ResponseEntity.ok().body(bno);
    }

    @GetMapping({"/read", "/modify"})
    public ResponseEntity<BoardDTO> read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long bno, Model model) {
        log.info("pno : " + bno);
        BoardDTO boardDTO = boardService.get(bno);
        log.info(boardDTO);
        model.addAttribute("dto", boardDTO);

        return new ResponseEntity<>(boardService.get(bno), HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<Long> remove(@RequestBody BoardDTO boardDTO) {

        boardService.remove(boardDTO);
        return ResponseEntity.ok(boardDTO.getBno());
    }

    @PostMapping("/modify")
    public ResponseEntity<Long> modify(@RequestBody BoardDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {

        log.info("post modify..................................");
        log.info("dto : "+ dto);

        boardService.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());

        redirectAttributes.addAttribute("bno", dto.getBno());
        return ResponseEntity.ok(dto.getBno());
    }
}
