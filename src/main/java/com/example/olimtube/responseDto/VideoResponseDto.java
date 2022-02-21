package com.example.olimtube.responseDto;

import com.example.olimtube.model.Video;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class VideoResponseDto {
    private Long video_id;
    private String title;
    private String img;
    private int categroyNumber;
    private String category_img;
    private int views;
    private int likes;
    private LocalDateTime createdAt;
    private VideoUserResponseDto videoUserResponseDto;

    public VideoResponseDto(Video video, VideoUserResponseDto videoUserResponseDto) {
        this.video_id = video.getId();
        this.title = video.getTitle();
        this.img = video.getImg();
        this.categroyNumber = video.getCategory().getCategoryNumber();
        this.category_img = video.getCategory().getCategoryImg();
        this.views = video.getViews();
        this.likes = video.getLikes();
        this.createdAt = video.getCreatedAt();
        this.videoUserResponseDto = videoUserResponseDto;
    }
}
