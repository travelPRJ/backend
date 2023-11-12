package com.iot.travel.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "ppno")
@Table(name = "PlannerLoc")
public class PlannerLoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plno;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ppno")
    private Planner ppno;    // 참조하는 Planner 테이블의 pno
    private String placeName;   // 장소 이름
    private String transport;   // 교통 수단
    private String region;         // 지역 태그
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lstart;   // 방문 시간
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lend;     // 떠난 시간
    private Integer ldelete;        // 0 과 1로 삭제 여부 구분
    private Double lat; // 위도
    private Double lng; // 경도
}