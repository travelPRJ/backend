package com.iot.travel.dto;

import lombok.*;

import java.time.LocalDate;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlannerLocDTO {
    private Long plno;
    private Long ppno;
    private String placeName;
    private String transport;
    private String region;
    private LocalDate lstart;
    private LocalDate lend;
    private Integer ldelete = 0;
    private Double lat;
    private Double lng;
}
