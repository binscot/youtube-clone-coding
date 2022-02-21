package com.example.olimtube.model;

import com.example.olimtube.requestDto.VideoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@Table(name="video")
public class Video extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="video_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String img;

    @Column(nullable = false)
    private int likes;

    @Column(nullable = false)
    private int views;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public Video(VideoRequestDto videoRequestDto, String img, User user, Category category) {
        this.title = videoRequestDto.getTitle();
        this.likes = 0;
        this.views = 0;
        this.img = img;
        this.user = user;
        this.category = category;
    }
}
