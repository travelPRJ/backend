package com.iot.travel.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReplyDTO {

    private Long rno;
    private Long runo;
    private Long rbno;
    private String rcontent;
    private Integer rdelete = 0;
    private LocalDate regDate;
    private LocalDate modDate;
}
