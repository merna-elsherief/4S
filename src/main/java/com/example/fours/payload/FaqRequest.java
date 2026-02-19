package com.example.fours.payload;

import lombok.Data;
import lombok.Builder;

@Data
public class FaqRequest {
    private String questionEn;
    private String answerEn;
    private String questionAr;
    private String answerAr;
    private String category;
    private Integer order;
}