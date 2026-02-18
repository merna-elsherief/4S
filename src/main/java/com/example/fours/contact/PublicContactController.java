package com.example.fours.contact;

import com.example.fours.payload.ContactRequest;
import com.example.fours.payload.ContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class PublicContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactResponse> submitContactForm(@RequestBody ContactRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contactService.createContactMessage(request));
    }
}