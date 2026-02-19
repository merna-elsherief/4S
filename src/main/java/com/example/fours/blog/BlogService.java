package com.example.fours.blog;

import com.example.fours.exception.ResourceNotFoundException;
import com.example.fours.payload.BlogRequest;
import com.example.fours.payload.BlogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogResponse createBlog(BlogRequest request) {
        Blog blog = Blog.builder()
                .titleEn(request.getTitleEn())
                .titleAr(request.getTitleAr())
                .slug(generateSlug(request.getTitleEn()))
                .briefEn(request.getBriefEn())
                .briefAr(request.getBriefAr())
                .contentEn(request.getContentEn())
                .contentAr(request.getContentAr())
                .metaTitleEn(request.getMetaTitleEn())
                .metaTitleAr(request.getMetaTitleAr())
                .metaDescriptionEn(request.getMetaDescriptionEn())
                .metaDescriptionAr(request.getMetaDescriptionAr())
                .coverImage(request.getCoverImage())
                .category(request.getCategory())
                .status(request.getStatus() != null ? request.getStatus() : "draft")
                .publishDate(request.getPublishDate() != null ? request.getPublishDate() : LocalDateTime.now())
                .build();

        Blog savedBlog = blogRepository.save(blog);
        return mapToResponse(savedBlog);
    }

    public Page<BlogResponse> getAllBlogs(int page, int size) {
        return blogRepository.findAll(PageRequest.of(page, size)).map(this::mapToResponse);
    }

    public BlogResponse getBlogBySlug(String slug) {
        Blog blog = blogRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with slug: " + slug));
        return mapToResponse(blog);
    }

    public BlogResponse updateBlog(String id, BlogRequest request) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with id: " + id));

        if (request.getTitleEn() != null) {
            blog.setTitleEn(request.getTitleEn());
            blog.setSlug(generateSlug(request.getTitleEn()));
        }
        if (request.getTitleAr() != null) blog.setTitleAr(request.getTitleAr());
        if (request.getBriefEn() != null) blog.setBriefEn(request.getBriefEn());
        if (request.getBriefAr() != null) blog.setBriefAr(request.getBriefAr());
        if (request.getContentEn() != null) blog.setContentEn(request.getContentEn());
        if (request.getContentAr() != null) blog.setContentAr(request.getContentAr());
        if (request.getMetaTitleEn() != null) blog.setMetaTitleEn(request.getMetaTitleEn());
        if (request.getMetaTitleAr() != null) blog.setMetaTitleAr(request.getMetaTitleAr());
        if (request.getMetaDescriptionEn() != null) blog.setMetaDescriptionEn(request.getMetaDescriptionEn());
        if (request.getMetaDescriptionAr() != null) blog.setMetaDescriptionAr(request.getMetaDescriptionAr());
        if (request.getCoverImage() != null) blog.setCoverImage(request.getCoverImage());
        if (request.getCategory() != null) blog.setCategory(request.getCategory());
        if (request.getStatus() != null) blog.setStatus(request.getStatus());
        if (request.getPublishDate() != null) blog.setPublishDate(request.getPublishDate());

        Blog updatedBlog = blogRepository.save(blog);
        return mapToResponse(updatedBlog);
    }

    public void deleteBlog(String id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with id: " + id));
        blogRepository.delete(blog);
    }

    private BlogResponse mapToResponse(Blog blog) {
        return BlogResponse.builder()
                .id(blog.getId())
                .slug(blog.getSlug())
                .titleEn(blog.getTitleEn())
                .titleAr(blog.getTitleAr())
                .briefEn(blog.getBriefEn())
                .briefAr(blog.getBriefAr())
                .contentEn(blog.getContentEn())
                .contentAr(blog.getContentAr())
                .metaTitleEn(blog.getMetaTitleEn())
                .metaTitleAr(blog.getMetaTitleAr())
                .metaDescriptionEn(blog.getMetaDescriptionEn())
                .metaDescriptionAr(blog.getMetaDescriptionAr())
                .coverImage(blog.getCoverImage())
                .category(blog.getCategory())
                .status(blog.getStatus())
                .publishDate(blog.getPublishDate())
                .build();
    }

    private String generateSlug(String title) {
        if (title == null) return "";
        return title.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("-$", "");
    }
}