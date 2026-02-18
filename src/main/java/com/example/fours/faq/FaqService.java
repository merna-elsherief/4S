package com.example.fours.faq;

import com.example.fours.exception.ResourceNotFoundException;
import com.example.fours.payload.FaqRequest;
import com.example.fours.payload.FaqResponse;
import com.example.fours.payload.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;

    public FaqResponse createFaq(FaqRequest request) {
        Faq faq = Faq.builder()
                .question(request.getQuestion())
                .answer(request.getAnswer())
                .build();
        Faq savedFaq = faqRepository.save(faq);
        return mapToResponse(savedFaq);
    }

    public PageResponse<FaqResponse> getAllFaqs(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Faq> faqsPage = faqRepository.findAll(pageable);

        List<FaqResponse> content = faqsPage.getContent().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return new PageResponse<>(
                content,
                faqsPage.getNumber(),
                faqsPage.getSize(),
                faqsPage.getTotalElements(),
                faqsPage.getTotalPages(),
                faqsPage.isLast()
        );
    }

    public FaqResponse updateFaq(String id, FaqRequest request) {
        Faq faq = faqRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FAQ not found with id: " + id));
        faq.setQuestion(request.getQuestion());
        faq.setAnswer(request.getAnswer());

        Faq updatedFaq = faqRepository.save(faq);
        return mapToResponse(updatedFaq);
    }

    public void deleteFaq(String id) {
        Faq faq = faqRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FAQ not found with id: " + id));        faqRepository.delete(faq);
    }

    private FaqResponse mapToResponse(Faq faq) {
        return FaqResponse.builder()
                .id(faq.getId())
                .question(faq.getQuestion())
                .answer(faq.getAnswer())
                .build();
    }
}