package com.example.fours.blog;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/admin/blogs")
@RequiredArgsConstructor
public class BlogAdminController {

    private final BlogService blogService;

    @PostMapping
    public Blog create(@RequestBody Blog blog) {
        return blogService.create(blog);
    }

    @GetMapping
    public Page<Blog> getAll(Pageable pageable) {
        return blogService.getAll(pageable);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        blogService.delete(id);
    }
}

