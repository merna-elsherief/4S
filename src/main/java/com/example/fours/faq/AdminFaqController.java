package com.example.fours.faq;

import com.example.fours.payload.FaqRequest;
import com.example.fours.payload.FaqResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/faqs")
@RequiredArgsConstructor
public class AdminFaqController {

    private final FaqService faqService;

    @PostMapping
    public ResponseEntity<FaqResponse> createFaq(@RequestBody FaqRequest request) {
        return new ResponseEntity<>(faqService.createFaq(request), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FaqResponse> updateFaq(@PathVariable String id, @RequestBody FaqRequest request) {
        return ResponseEntity.ok(faqService.updateFaq(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaq(@PathVariable String id) {
        faqService.deleteFaq(id);
        return ResponseEntity.noContent().build();
    }
}