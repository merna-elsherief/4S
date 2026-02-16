package com.example.fours.blog;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "blogs")
public class Blog {

    @Id
    private String id;

    private String title;

    @Indexed(unique = true)
    private String slug;

    private String content;

    private boolean published;

    private LocalDateTime createdAt;
}