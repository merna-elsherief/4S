package com.example.fours.contact;

import com.example.fours.payload.ContactRequest;
import com.example.fours.payload.ContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public void saveMessage(ContactRequest request) {
        Contact contact = Contact.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .subject(request.getSubject())
                .message(request.getMessage())
                .date(LocalDateTime.now())
                .build();
        contactRepository.save(contact);
    }

    public Page<ContactResponse> getAllMessages(int page, int size) {
        return contactRepository.findAll(PageRequest.of(page, size)).map(this::mapToResponse);
    }

    private ContactResponse mapToResponse(Contact contact) {
        return ContactResponse.builder()
                .id(contact.getId())
                .name(contact.getName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .subject(contact.getSubject())
                .message(contact.getMessage())
                .date(contact.getDate())
                .build();
    }
}