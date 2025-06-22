package com.shubhendukr.recostream.service;

import com.shubhendukr.recostream.dto.RecommendedContentDTO;
import com.shubhendukr.recostream.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ContentRepository contentRepo;

    public List<RecommendedContentDTO> searchContent(String query) {
        String lowerQuery = query.toLowerCase();

        return contentRepo.findAll().stream()
                .filter(c ->
                        c.getTitle().toLowerCase().contains(lowerQuery) ||
                        c.getTags().toLowerCase().contains(lowerQuery)
                )
                .map(c -> new RecommendedContentDTO(
                        c.getId(),
                        c.getTitle(),
                        c.getTags(),
                        c.getCreatedBy().getName()
                ))
                .collect(Collectors.toList());
    }
}
