package com.example.fours.contact;

import com.example.fours.payload.ContactRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class PublicContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<Map<String, Boolean>> submitContactForm(@RequestBody ContactRequest request) {
        contactService.saveMessage(request);
        return new ResponseEntity<>(Map.of("success", true), HttpStatus.CREATED);
    }
}