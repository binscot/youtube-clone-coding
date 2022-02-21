package com.example.olimtube.component;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class CategoryInfo {
    Map<Integer, String> map = new HashMap<Integer, String>();

    public CategoryInfo(){
        map.put(1, "https://youngbin.s3.ap-northeast-2.amazonaws.com/category/1.png");
        map.put(2, "https://youngbin.s3.ap-northeast-2.amazonaws.com/category/2.png");
        map.put(3, "https://youngbin.s3.ap-northeast-2.amazonaws.com/category/3.png");
        map.put(4, "https://youngbin.s3.ap-northeast-2.amazonaws.com/category/4.png");
        map.put(5, "https://youngbin.s3.ap-northeast-2.amazonaws.com/category/5.png");
        map.put(6, "https://youngbin.s3.ap-northeast-2.amazonaws.com/category/6.png");
        map.put(7, "https://youngbin.s3.ap-northeast-2.amazonaws.com/category/7.png");
        map.put(8, "https://youngbin.s3.ap-northeast-2.amazonaws.com/category/8.png");
        map.put(9, "https://youngbin.s3.ap-northeast-2.amazonaws.com/category/9.png");
        map.put(10, "https://youngbin.s3.ap-northeast-2.amazonaws.com/category/10.png");
    }
}
