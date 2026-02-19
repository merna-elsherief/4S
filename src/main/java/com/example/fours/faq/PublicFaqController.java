package com.example.fours.faq;

import com.example.fours.payload.FaqResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/faqs")
@RequiredArgsConstructor
public class PublicFaqController {

    private final FaqService faqService;

    @GetMapping
    public ResponseEntity<Page<FaqResponse>> getAllFaqs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size) {
        return ResponseEntity.ok(faqService.getAllFaqs(page, size));
    }
}