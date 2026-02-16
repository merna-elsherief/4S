package com.example.fours.blog;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BlogRepository extends MongoRepository<Blog, String> {

    Optional<Blog> findBySlug(String slug);

    Page<Blog> findByPublishedTrue(Pageable pageable);

    boolean existsBySlug(String slug);
}
