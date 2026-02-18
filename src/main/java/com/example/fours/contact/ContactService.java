package com.example.fours.contact;

import com.example.fours.payload.ContactRequest;
import com.example.fours.payload.ContactResponse;
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
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactResponse createContactMessage(ContactRequest request) {
        Contact contact = Contact.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .subject(request.getSubject())
                .message(request.getMessage())
                .build();

        Contact savedContact = contactRepository.save(contact);
        return mapToResponse(savedContact);
    }

    public PageResponse<ContactResponse> getAllContactMessages(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());
        Page<Contact> contactsPage = contactRepository.findAll(pageable);

        List<ContactResponse> content = contactsPage.getContent().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return new PageResponse<>(
                content,
                contactsPage.getNumber(),
                contactsPage.getSize(),
                contactsPage.getTotalElements(),
                contactsPage.getTotalPages(),
                contactsPage.isLast()
        );
    }

    private ContactResponse mapToResponse(Contact contact) {
        return ContactResponse.builder()
                .id(contact.getId())
                .name(contact.getName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .subject(contact.getSubject())
                .message(contact.getMessage())
                .createdAt(contact.getCreatedAt())
                .build();
    }
}