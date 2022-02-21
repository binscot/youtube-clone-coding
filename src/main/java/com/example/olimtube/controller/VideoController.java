package com.example.olimtube.controller;

import com.example.olimtube.component.S3Uploader;
import com.example.olimtube.model.User;
import com.example.olimtube.requestDto.VideoRequestDto;
import com.example.olimtube.security.UserDetailsImpl;
import com.example.olimtube.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;
    private final S3Uploader s3Uploader;

    @PostMapping("/upload")
    public ResponseEntity uploadVideo(@RequestPart(value = "contents")VideoRequestDto videoRequestDto,
                                      @RequestPart(value = "img", required = false) MultipartFile multipartFile,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        String img = "";
        if(!multipartFile.isEmpty()) img = s3Uploader.upload(multipartFile, "static");
        User user = userDetails.getUser();
        videoService.uploadVideo(videoRequestDto, img, user);

        return ResponseEntity.ok().body(null);
    }
}
