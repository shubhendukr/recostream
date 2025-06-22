package com.shubhendukr.recostream.controller;

import com.shubhendukr.recostream.dto.RecommendedContentDTO;
import com.shubhendukr.recostream.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public List<RecommendedContentDTO> search(@RequestParam("q") String query) {
        return searchService.searchContent(query);
    }
}
