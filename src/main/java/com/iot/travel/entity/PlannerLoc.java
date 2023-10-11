package com.iot.travel.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "PlannerLoc")
public class PlannerLoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plno;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pno")
    private Planner ppno;    // 참조하는 Planner 테이블의 pno
    private String placeName;   // 장소 이름
    private String address;     // 주소
    private String transport;   // 교통 수단
    private String tag;         // 지역 태그
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lStart;   // 방문 시간
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lEnd;     // 떠난 시간
    private Double lat; // 위도
    private Double lng; // 경도
}
