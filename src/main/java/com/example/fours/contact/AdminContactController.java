package com.example.fours.contact;

import com.example.fours.payload.ContactResponse;
import com.example.fours.payload.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/contact")
@RequiredArgsConstructor
public class AdminContactController {

    private final ContactService contactService;

    @GetMapping
    public ResponseEntity<PageResponse<ContactResponse>> getAllContactMessages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(contactService.getAllContactMessages(page, size));
    }
}