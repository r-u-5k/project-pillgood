package com.pillgood.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRequestDto {
    private Long no;
    private String title;
    private String name;
    private String content;
    private Long rating;
    private Long userNo;
    private Long ItemNo;
} 