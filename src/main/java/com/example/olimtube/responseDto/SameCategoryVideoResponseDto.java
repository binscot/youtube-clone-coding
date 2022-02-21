package com.example.olimtube.responseDto;

import com.example.olimtube.model.Video;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SameCategoryVideoResponseDto {
    private Long video_id;
    private String img;
    private String title;
    private int views;

    public SameCategoryVideoResponseDto(Video video) {
        this.video_id = video.getId();
        this.img = video.getImg();
        this.title = video.getTitle();
        this.views = video.getViews();
    }
}
