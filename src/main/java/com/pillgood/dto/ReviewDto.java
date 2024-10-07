package com.pillgood.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {
    private Long no;
    private String title;
    private String name;
    private String content;
    private Long rating;
    private Long userNo;
    private Date birthday;
    private String gender;
    private ItemDto itemDto;
    private Date date;
}