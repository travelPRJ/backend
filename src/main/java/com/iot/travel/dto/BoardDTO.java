package com.iot.travel.dto;

import lombok.*;

import java.time.LocalDate;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {

    private Long bno;
    private Long buno;
    private String btitle;
    private String bcontent;
    private Integer bdelete =0;
    private LocalDate regDate;
    private LocalDate modDate;
    private int replyCount;

}
