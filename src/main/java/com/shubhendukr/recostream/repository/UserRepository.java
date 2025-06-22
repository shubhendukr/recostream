package com.shubhendukr.recostream.repository;

import com.shubhendukr.recostream.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
