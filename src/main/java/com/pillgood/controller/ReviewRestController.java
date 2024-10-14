package com.pillgood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pillgood.dto.ReviewDto;
import com.pillgood.dto.ReviewRequestDto;
import com.pillgood.service.ReviewService;

@RequestMapping("/api")
@RestController
public class ReviewRestController {
	
	@Autowired
	ReviewService reviewService;
	
	@PostMapping("/review")
	public ResponseEntity<ReviewRequestDto> createReview(@RequestBody ReviewRequestDto reviewDto) {
		ReviewRequestDto responseReviewDto = reviewService.createReview(reviewDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseReviewDto);
	}
	
	@GetMapping("/review/{itemId}")
	public ResponseEntity<List<ReviewDto>> findReviewByItem(@PathVariable(name = "itemId") Long itemId) {
		List<ReviewDto> reviewListDto = reviewService.findReviewByItem(itemId);
		return ResponseEntity.status(HttpStatus.OK).body(reviewListDto);
	}
	
	@GetMapping("/review")
	public ResponseEntity<List<ReviewDto>> findReviewAll() {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		List<ReviewDto> reviewListDto = reviewService.findReviewAll();
		return ResponseEntity.status(HttpStatus.OK).body(reviewListDto);
	}
	
	@GetMapping("/review/user/{userId}")
	public ResponseEntity<List<ReviewDto>> findReviewByUserId(@PathVariable(name = "userId") Long userId) {
		List<ReviewDto> reviewListDto = reviewService.findReviewByUserId(userId);
		return ResponseEntity.status(HttpStatus.OK).body(reviewListDto);
	}
	
	
	

}
