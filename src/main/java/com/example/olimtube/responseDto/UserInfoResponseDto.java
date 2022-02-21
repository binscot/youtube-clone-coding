package com.example.olimtube.responseDto;

import com.example.olimtube.model.Category;
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
    private List<Category> categories;
}




