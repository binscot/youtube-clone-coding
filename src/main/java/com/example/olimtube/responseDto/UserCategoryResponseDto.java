package com.example.olimtube.responseDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCategoryResponseDto {
    private int categoryNumber;
    private String category_img;


    public UserCategoryResponseDto(int categoryNumber, String category_img) {
        this.categoryNumber=categoryNumber;
        this.category_img=category_img;
    }
}
