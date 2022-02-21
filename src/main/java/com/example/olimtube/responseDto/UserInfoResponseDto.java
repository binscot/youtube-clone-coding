package com.example.olimtube.responseDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserInfoResponseDto {
    private Long id;
    private String username;
    private String profile;
    private Boolean is_login;
    List<UserCategoryResponseDto> userCategoryResponseDtoList;
}




