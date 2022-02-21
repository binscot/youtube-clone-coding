package com.example.olimtube.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class UserCatecory {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="category_id")
    private Long id;

    @Column(nullable = false)
    private int categoryNumber;

    @Column(nullable = false)
    private String categoryImg;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public UserCatecory(int categoryNumber, String categoryImg, User user) {
        this.categoryNumber=categoryNumber;
        this.categoryImg=categoryImg;
        this.user=user;
    }
}
