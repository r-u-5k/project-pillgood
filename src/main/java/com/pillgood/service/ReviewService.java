package com.pillgood.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pillgood.dto.ReviewDto;
import com.pillgood.dto.ReviewRequestDto;
import com.pillgood.entity.Review;
import com.pillgood.mapper.ToDtoMapper;
import com.pillgood.repository.ItemRepository;
import com.pillgood.repository.ReviewRepository;
import com.pillgood.repository.UserRepository;

@Service
public class ReviewService {
	@Autowired
	ReviewRepository reviewRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ItemRepository itemRepository;
	
	public ReviewRequestDto createReview(ReviewRequestDto reviewDto) {
		Review review = Review.builder()
				.content(reviewDto.getContent())
				.star(reviewDto.getRating())
				.title(reviewDto.getTitle())
				.reviewDate(new Date())
				.user(userRepository.findById(reviewDto.getUserNo()).get())
				.item(itemRepository.findByNo(reviewDto.getItemNo()))			
				.build();
		reviewRepository.save(review);
		return reviewDto;
	}
	
	public List<ReviewDto> findReviewByItem(Long itemId) {
		List<Review> reviewList = reviewRepository.findByItemNoOrderByReviewDateDesc(itemId);
		List<ReviewDto> reviewListDto = reviewList.stream()
				.map(ToDtoMapper::toReviewDto)
				.collect(Collectors.toList());
		return reviewListDto;
		
	}
	public List<ReviewDto> findReviewAll() {
		List<Review> reviewList = reviewRepository.findAllByOrderByReviewDateDesc();
		System.out.println(reviewList);
		List<ReviewDto> reviewListDto = reviewList.stream()
				.map(ToDtoMapper::toReviewDto)
				.collect(Collectors.toList());
		System.out.println(reviewListDto);
		return reviewListDto;
	}

	public List<ReviewDto> findReviewByUserId(Long userId) {
		List<Review> review =reviewRepository.findByUserId(userId);
		List<ReviewDto> reviewListDto = review.stream().map(ToDtoMapper::toReviewDto).collect(Collectors.toList());
		return reviewListDto;
	}
}
