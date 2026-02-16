package com.example.fours.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping
    public Page<Blog> getAll(Pageable pageable) {
        return blogService.getPublished(pageable);
    }

    @GetMapping("/{slug}")
    public Blog getOne(@PathVariable String slug) {
        return blogService.getBySlug(slug);
    }
}
