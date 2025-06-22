package com.shubhendukr.recostream.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecommendedContentDTO {
    private Long id;
    private String title;
    private String tags;
    private String createdBy;
}
