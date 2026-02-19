package com.example.fours.payload;

import lombok.Data;
import lombok.Builder;
import java.time.LocalDateTime;

@Data
@Builder
public class BlogResponse {
    private String id;
    private String slug;
    private String titleEn;
    private String titleAr;
    private String briefEn;
    private String briefAr;
    private String contentEn;
    private String contentAr;
    private String metaTitleEn;
    private String metaTitleAr;
    private String metaDescriptionEn;
    private String metaDescriptionAr;
    private String coverImage;
    private String category;
    private String status;
    private LocalDateTime publishDate;
}