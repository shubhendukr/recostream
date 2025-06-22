package com.shubhendukr.recostream.repository;

import com.shubhendukr.recostream.model.Content;
import com.shubhendukr.recostream.model.User;
import com.shubhendukr.recostream.model.UserInteraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInteractionRepository extends JpaRepository<UserInteraction, Long> {
    List<UserInteraction> findByUser(User user);
    List<UserInteraction> findByContent(Content content);
}
