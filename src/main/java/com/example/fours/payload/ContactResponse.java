package com.example.fours.payload;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ContactResponse {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String subject;
    private String message;
    private LocalDateTime createdAt;
}