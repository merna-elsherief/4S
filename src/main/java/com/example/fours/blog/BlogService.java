package com.example.fours.blog;

import com.example.fours.common.SlugUtils;
import com.example.fours.exception.ResourceNotFoundException;
import com.example.fours.payload.BlogRequest;
import com.example.fours.payload.BlogResponse;
import com.example.fours.payload.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final SlugUtils slugUtils;

    public BlogResponse createBlog(BlogRequest request) {
        String baseSlug = slugUtils.generateSlug(request.getTitle());
        String uniqueSlug = generateUniqueSlug(baseSlug);

        Blog blog = Blog.builder()
                .title(request.getTitle())
                .slug(uniqueSlug)
                .content(request.getContent())
                .imageUrl(request.getImageUrl())
                .build();

        Blog savedBlog = blogRepository.save(blog);
        return mapToResponse(savedBlog);
    }
    public PageResponse<BlogResponse> getAllBlogs(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());
        Page<Blog> blogsPage = blogRepository.findAll(pageable);

        List<BlogResponse> content = blogsPage.getContent().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return new PageResponse<>(
                content,
                blogsPage.getNumber(),
                blogsPage.getSize(),
                blogsPage.getTotalElements(),
                blogsPage.getTotalPages(),
                blogsPage.isLast()
        );
    }

    public BlogResponse getBlogBySlug(String slug) {
        Blog blog = blogRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Blog not found with slug: " + slug
                ));  return mapToResponse(blog);
    }

    public BlogResponse updateBlog(String id, BlogRequest request) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Blog not found with id: " + id
                ));
        blog.setTitle(request.getTitle());
        blog.setContent(request.getContent());
        blog.setImageUrl(request.getImageUrl());

        Blog updatedBlog = blogRepository.save(blog);
        return mapToResponse(updatedBlog);
    }
    public void deleteBlog(String id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Blog not found with id: " + id
                ));
    }

    private BlogResponse mapToResponse(Blog blog) {
        return BlogResponse.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .slug(blog.getSlug())
                .content(blog.getContent())
                .imageUrl(blog.getImageUrl())
                .createdAt(blog.getCreatedAt())
                .updatedAt(blog.getUpdatedAt())
                .build();
    }

    private String generateUniqueSlug(String baseSlug) {
        String uniqueSlug = baseSlug;
        int counter = 1;
        while (blogRepository.findBySlug(uniqueSlug).isPresent()) {
            uniqueSlug = baseSlug + "-" + counter;
            counter++;
        }
        return uniqueSlug;
    }
}