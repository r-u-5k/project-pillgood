package com.pillgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pillgood.entity.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
	

}
