package com.example.fours.common;

import org.springframework.stereotype.Component;

@Component
public class SlugUtils {

    public String generateSlug(String title) {
        if (title == null || title.isEmpty()) {
            return "";
        }
        return title.trim()
                .toLowerCase()
                .replaceAll("[\\s_]+", "-")
                .replaceAll("[^a-z0-9\\u0600-\\u06FF-]", "")
                .replaceAll("-{2,}", "-")
                .replaceAll("^-|-$", "");
    }
}