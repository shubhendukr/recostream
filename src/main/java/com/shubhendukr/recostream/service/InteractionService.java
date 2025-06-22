package com.shubhendukr.recostream.service;

import com.shubhendukr.recostream.model.Content;
import com.shubhendukr.recostream.model.InteractionType;
import com.shubhendukr.recostream.model.User;
import com.shubhendukr.recostream.model.UserInteraction;
import com.shubhendukr.recostream.repository.ContentRepository;
import com.shubhendukr.recostream.repository.UserInteractionRepository;
import com.shubhendukr.recostream.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InteractionService {

    private final UserRepository userRepo;
    private final ContentRepository contentRepo;
    private final UserInteractionRepository interactionRepo;

    public void recordInteraction(Long userId, Long contentId, InteractionType type) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Content content = contentRepo.findById(contentId)
                .orElseThrow(() -> new RuntimeException("Content not found"));

        UserInteraction interaction = new UserInteraction(
                null,
                user,
                content,
                type,
                LocalDateTime.now()
        );

        interactionRepo.save(interaction);
    }
}
