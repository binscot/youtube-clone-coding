package com.example.olimtube.responseDto;

import com.example.olimtube.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoUserResponseDto {
    private Long user_id;
    private String username;
    private String profile;

    public VideoUserResponseDto(User user) {
        this.user_id = user.getId();
        this.username = user.getUsername();
        this.profile = user.getProfile();
    }
}
