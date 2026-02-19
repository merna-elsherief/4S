package com.example.fours.faq;

import com.example.fours.exception.ResourceNotFoundException;
import com.example.fours.payload.FaqRequest;
import com.example.fours.payload.FaqResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;

    public FaqResponse createFaq(FaqRequest request) {
        Faq faq = Faq.builder()
                .questionEn(request.getQuestionEn())
                .answerEn(request.getAnswerEn())
                .questionAr(request.getQuestionAr())
                .answerAr(request.getAnswerAr())
                .category(request.getCategory())
                .order(request.getOrder())
                .build();
        Faq savedFaq = faqRepository.save(faq);
        return mapToResponse(savedFaq);
    }

    public Page<FaqResponse> getAllFaqs(int page, int size) {
        return faqRepository.findAll(PageRequest.of(page, size)).map(this::mapToResponse);
    }

    public FaqResponse updateFaq(String id, FaqRequest request) {
        Faq faq = faqRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FAQ not found with id: " + id));

        if (request.getQuestionEn() != null) faq.setQuestionEn(request.getQuestionEn());
        if (request.getAnswerEn() != null) faq.setAnswerEn(request.getAnswerEn());
        if (request.getQuestionAr() != null) faq.setQuestionAr(request.getQuestionAr());
        if (request.getAnswerAr() != null) faq.setAnswerAr(request.getAnswerAr());
        if (request.getCategory() != null) faq.setCategory(request.getCategory());
        if (request.getOrder() != null) faq.setOrder(request.getOrder());

        Faq updatedFaq = faqRepository.save(faq);
        return mapToResponse(updatedFaq);
    }

    public void deleteFaq(String id) {
        Faq faq = faqRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FAQ not found with id: " + id));
        faqRepository.delete(faq);
    }

    private FaqResponse mapToResponse(Faq faq) {
        return FaqResponse.builder()
                .id(faq.getId())
                .questionEn(faq.getQuestionEn())
                .answerEn(faq.getAnswerEn())
                .questionAr(faq.getQuestionAr())
                .answerAr(faq.getAnswerAr())
                .category(faq.getCategory())
                .order(faq.getOrder())
                .build();
    }
}