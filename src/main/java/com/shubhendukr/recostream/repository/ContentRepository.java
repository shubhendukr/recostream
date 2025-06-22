package com.shubhendukr.recostream.repository;

import com.shubhendukr.recostream.model.Content;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {

    @Query("SELECT c FROM Content c WHERE " +
            "LOWER(c.tags) LIKE(CONCAT('%', :tag, '%'))")
    List<Content> findByTagLike(@Param("tag") String tag, Pageable pageable);
}
