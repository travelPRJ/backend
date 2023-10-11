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
@Table(name = "Planner")
public class Planner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uno")
    private User puno;          // 참조하는 User 테이블의 pno
    private Integer pCount;         // 스크랩 시 카운트 증가
    private Integer pDelete;        // 0 과 1로 삭제 여부 구분
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 년,월,일 만 받기 위해 LocalDate 사용
    private LocalDate pStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pEnd;
}
