package com.example.fours.blog;

import com.example.fours.common.SlugUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final SlugUtil slugUtil;

    public Blog create(Blog blog) {

        String baseSlug = slugUtil.generateSlug(blog.getTitle());
        String slug = baseSlug;
        int counter = 1;

        // ensure unique slug
        while (blogRepository.existsBySlug(slug)) {
            slug = baseSlug + "-" + counter++;
        }

        blog.setSlug(slug);
        blog.setCreatedAt(LocalDateTime.now());

        return blogRepository.save(blog);
    }

    public Page<Blog> getPublished(Pageable pageable) {
        return blogRepository.findByPublishedTrue(pageable);
    }

    public Page<Blog> getAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    public Blog getBySlug(String slug) {
        return blogRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    public void delete(String id) {
        blogRepository.deleteById(id);
    }
}
