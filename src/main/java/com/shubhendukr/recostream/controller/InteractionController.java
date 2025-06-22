package com.shubhendukr.recostream.controller;

import com.shubhendukr.recostream.model.InteractionType;
import com.shubhendukr.recostream.service.InteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/interactions")
@RequiredArgsConstructor
public class InteractionController {

    private final InteractionService interactionService;

    @PostMapping
    public void recordInteraction(
            @RequestParam Long userId,
            @RequestParam Long contentId,
            @RequestParam InteractionType type
    ) {
        interactionService.recordInteraction(userId, contentId, type);
    }
}
