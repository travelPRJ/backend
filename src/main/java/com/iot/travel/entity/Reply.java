package com.iot.travel.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Table(name = "reply")
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String rcontent; // 댓글 내용

    private Integer rdelete;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "runo")
    private User runo; // 댓글 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rbno")
    private Board rbno; // 댓글이 작성된 게시글 번호

    public void changeContent(String rcontent) { this.rcontent = rcontent; }
    public void changeDelete(Integer rdelete) { this.rdelete = rdelete; }
}
