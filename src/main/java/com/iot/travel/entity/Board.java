package com.iot.travel.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "buno")
@Table(name = "Board")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buno")
    private User buno;                  // 참조하는 User 테이블의 uno
    private String btitle;              // 글 제목
    private Integer bdelete;            // 삭제 여부 구분
    private String bcontent;             // 글 내용

    public void changeTitle(String btitle) {
        this.btitle = btitle;
    }
    public void changeContent(String bcontent) {
        this.bcontent = bcontent;
    }
    public void changeDelete(Integer bdelete) {
        this.bdelete = bdelete;
    }
}
