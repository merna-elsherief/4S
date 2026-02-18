package com.example.fours.faq;

import com.example.fours.payload.FaqRequest;
import com.example.fours.payload.FaqResponse;
import com.example.fours.payload.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/faqs")
@RequiredArgsConstructor
public class AdminFaqController {

    private final FaqService faqService;

    @GetMapping
    public ResponseEntity<PageResponse<FaqResponse>> getAllFaqs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(faqService.getAllFaqs(page, size));
    }

    @PostMapping
    public ResponseEntity<FaqResponse> createFaq(@RequestBody FaqRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(faqService.createFaq(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FaqResponse> updateFaq(
            @PathVariable String id,
            @RequestBody FaqRequest request) {
        return ResponseEntity.ok(faqService.updateFaq(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaq(@PathVariable String id) {
        faqService.deleteFaq(id);
        return ResponseEntity.noContent().build();
    }
}