package com.shubhendukr.recostream.service;

import com.shubhendukr.recostream.dto.RecommendedContentDTO;
import com.shubhendukr.recostream.model.Content;
import com.shubhendukr.recostream.model.User;
import com.shubhendukr.recostream.repository.ContentRepository;
import com.shubhendukr.recostream.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final UserRepository userRepo;
    private final ContentRepository contentRepo;

    public List<RecommendedContentDTO> getRecommendations(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<String> userInterests = Arrays.stream(user.getInterests().split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        List<Content> allContent = contentRepo.findAll();

        return allContent.stream()
                .map(content -> {
                    Set<String> contentTags = Arrays.stream(content.getTags().split(","))
                            .map(String::trim)
                            .map(String::toLowerCase)
                            .collect(Collectors.toSet());

                    long score = contentTags.stream().filter(userInterests::contains).count();

                    return new AbstractMap.SimpleEntry<>(content, score);
                })
                .filter(entry -> entry.getValue() > 0)  // only get relevant content
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue())) // sort by score descending
                .map(entry -> {
                    Content c = entry.getKey();
                    return new RecommendedContentDTO(
                            c.getId(),
                            c.getTitle(),
                            c.getTags(),
                            c.getCreatedBy().getName()
                    );
                })
                .collect(Collectors.toList());
    }
}
