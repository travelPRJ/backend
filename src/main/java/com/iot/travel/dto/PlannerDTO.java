package com.iot.travel.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlannerDTO {
    private Long pno;
    private Long puno;
    private String ptitle;
    private String nickname;
    private Integer pcount =0;
    private Integer pdelete =0;
    private LocalDate pstart;
    private LocalDate pend;
}
