package com.example.fours.blog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "blogs")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
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