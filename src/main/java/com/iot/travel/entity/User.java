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
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uno;
    private String nickname;


    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

}
