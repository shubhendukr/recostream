package com.shubhendukr.recostream.config;

import com.shubhendukr.recostream.model.Content;
import com.shubhendukr.recostream.model.User;
import com.shubhendukr.recostream.repository.ContentRepository;
import com.shubhendukr.recostream.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepo;
    private final ContentRepository contentRepo;

    @Override
    public void run(String... args) throws Exception {
        if (userRepo.count() > 0) return;   // only load once

        // Create User
        User alice = userRepo.save(new User(null, "Alice", "tech,ai, ml"));
        User bob = userRepo.save(new User(null, "Bob", "travel,music,photography"));
        User carol = userRepo.save(new User(null, "Carol", "cooking,fitness,health"));

        // Create Content
        contentRepo.save(new Content(null, "Intro to AI", "tech,ai", alice, LocalDateTime.now()));
        contentRepo.save(new Content(null, "Top 10 Travel Destinations", "travel,adventure", bob, LocalDateTime.now()));
        contentRepo.save(new Content(null, "5 Healthy Breakfast Ideas", "health,cooking", carol, LocalDateTime.now()));
        contentRepo.save(new Content(null, "Deep Learning Trends", "tech,ml,ai", alice, LocalDateTime.now()));
        contentRepo.save(new Content(null, "Solo Travel Guide", "travel,lifestyle", bob, LocalDateTime.now()));
    }
}
