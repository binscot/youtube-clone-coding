package com.example.olimtube.responseDto;

import com.example.olimtube.model.Category;
import com.example.olimtube.model.User;
import com.example.olimtube.model.Video;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VideoDetailResponeDto {
    private Long video_id;
    private String title;
    private String img;
    private int category;
    private String category_img;
    //비디오파일
    private int views;
    private int likes;
    private VideoUserResponseDto videoUserResponseDto;
    private List<SameCategoryVideoResponseDto> sameCategoryVideoResponseDtos;

    public VideoDetailResponeDto(VideoUserResponseDto videoUserResponseDto, Video video, Category category, List<SameCategoryVideoResponseDto> sameCategoryVideoResponseDtos) {
        this.video_id = video.getId();
        this.title = video.getTitle();
        this.img = video.getImg();
        this.category = category.getCategoryNumber();
        this.category_img = category.getCategoryImg();
        this.views = video.getViews();
        this.likes = video.getLikes();
        this.videoUserResponseDto = videoUserResponseDto;
        this.sameCategoryVideoResponseDtos = sameCategoryVideoResponseDtos;
    }
}
