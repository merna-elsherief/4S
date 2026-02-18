package com.example.fours.blog;

import com.example.fours.payload.BlogRequest;
import com.example.fours.payload.BlogResponse;
import com.example.fours.payload.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/blogs")
@RequiredArgsConstructor
public class AdminBlogController {

    private final BlogService blogService;
    @GetMapping
    public ResponseEntity<PageResponse<BlogResponse>> getAllBlogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(blogService.getAllBlogs(page, size));
    }

    @PostMapping
    public ResponseEntity<BlogResponse> createBlog(@RequestBody BlogRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                blogService.createBlog(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BlogResponse> updateBlog(
            @PathVariable String id,
            @RequestBody BlogRequest request) {
        return ResponseEntity.ok(blogService.updateBlog(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable String id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}