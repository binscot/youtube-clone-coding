package com.example.olimtube.service;

import com.example.olimtube.model.Category;
import com.example.olimtube.model.User;
import com.example.olimtube.model.Video;
import com.example.olimtube.repository.VideoRepository;
import com.example.olimtube.requestDto.VideoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final CategoryService categoryService;

    @Transactional
    public void uploadVideo(VideoRequestDto videoRequestDto, String img, User user) {
        Category category = categoryService.createCategory(videoRequestDto.getCategory());

        Video video = new Video(videoRequestDto, img, user, category);

        videoRepository.save(video);
    }
}
