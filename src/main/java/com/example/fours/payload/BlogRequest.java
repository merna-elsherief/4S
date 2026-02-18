package com.example.fours.payload;

import lombok.Data;

@Data
public class BlogRequest {
    private String title;
    private String content;
    private String imageUrl;
}