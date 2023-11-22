package com.iot.travel.controller;

import com.iot.travel.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.iot.travel.dto.ReplyDTO;
import com.iot.travel.service.ReplyService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/reply")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping(value = "/board/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDTO>> getListByBoard(@PathVariable("bno") Long bno) {
        log.info("bno: " + bno);
        List<ReplyDTO> replyDTOList = replyService.getList(bno);
        replyDTOList.forEach(replyDTO -> System.out.println(replyDTO));

        return new ResponseEntity<>(replyService.getList(bno), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO) {
        log.info(replyDTO);

        Long rno = replyService.register(replyDTO);

        return new ResponseEntity<>(rno, HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<Long> remove(@RequestBody ReplyDTO replyDTO) {

        replyService.remove(replyDTO);
        return ResponseEntity.ok(replyDTO.getRno());
    }

    @PostMapping("/modify")
    public ResponseEntity<Long> modify(@RequestBody ReplyDTO replyDTO, RedirectAttributes redirectAttributes) {
        log.info("post modify..................................");
        log.info("dto : "+ replyDTO);

        replyService.modify(replyDTO);

        redirectAttributes.addAttribute("rno", replyDTO.getRno());
        return ResponseEntity.ok(replyDTO.getRno());
    }



}
