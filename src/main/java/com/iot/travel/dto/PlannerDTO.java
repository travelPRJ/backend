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
    private String nickname;
    private Integer pCount;
    private Integer pDelete;
    private LocalDate pStart;
    private LocalDate pEnd;
}
