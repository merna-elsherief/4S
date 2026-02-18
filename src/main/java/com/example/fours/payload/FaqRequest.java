package com.example.fours.payload;

import lombok.Data;

@Data
public class FaqRequest {
    private String question;
    private String answer;
}