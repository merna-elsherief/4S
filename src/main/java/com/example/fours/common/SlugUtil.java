package com.example.fours.common;


import org.springframework.stereotype.Component;

@Component
public class SlugUtil {

    public String generateSlug(String title) {
        return title.toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("(^-|-$)", "");
    }
}
