package com.iot.travel.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Scrap")
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uno")
    private User suno;          // 참조하는 User 테이블의 pno
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pno")
    private Planner spno;    // 참조하는 Planner 테이블의 pno
    private Integer sFlag;      // 0 과 1로 삭제 여부 구분
}
