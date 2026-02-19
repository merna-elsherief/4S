package com.example.fours.faq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "faqs")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Faq {
    @Id
    private String id;
    private String questionEn;
    private String answerEn;
    private String questionAr;
    private String answerAr;
    private String category;
    private Integer order;
}