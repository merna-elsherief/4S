package com.example.fours.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FaqResponse {
    private String id;
    private String question;
    private String answer;
}