package com.pillgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pillgood.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByItemNoOrderByReviewDateDesc(Long itemNo);

    List<Review> findByUserId(Long userNo);
    
    List<Review> findAllByOrderByReviewDateDesc();
}