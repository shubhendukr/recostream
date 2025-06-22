package com.shubhendukr.recostream.service;

import com.shubhendukr.recostream.dto.RecommendedContentDTO;
import com.shubhendukr.recostream.model.Content;
import com.shubhendukr.recostream.model.User;
import com.shubhendukr.recostream.repository.ContentRepository;
import com.shubhendukr.recostream.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final UserRepository userRepo;
    private final ContentRepository contentRepo;

    public List<RecommendedContentDTO> getRecommendations(Long userId, int page, int size) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<String> userInterests = Arrays.stream(user.getInterests().split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        Set<Content> matchedContent = new HashSet<>();
        Pageable pageable = PageRequest.of(page, size);

        for (String tag : userInterests) {
            List<Content> byTag = contentRepo.findByTagLike(tag, pageable);
            matchedContent.addAll(byTag);
        }

        return matchedContent.stream()
                .sorted((a, b) -> {
                    long scoreA = scoreContent(a.getTags(), userInterests);
                    long scoreB = scoreContent(b.getTags(), userInterests);

                    return Long.compare(scoreB, scoreA);    // higher score first
                })
                .map(c -> new RecommendedContentDTO(
                        c.getId(),
                        c.getTitle(),
                        c.getTags(),
                        c.getCreatedBy().getName()
                ))
                .collect(Collectors.toList());
    }

    private long scoreContent(String contentTags, Set<String> userInterests) {
        Set<String> tags = Arrays.stream(contentTags.split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        return tags.stream().filter(userInterests::contains).count();
    }
}
