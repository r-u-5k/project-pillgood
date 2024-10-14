package com.pillgood.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pillgood.dto.SurveyDto;
import com.pillgood.service.SurveyService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class SurveyRestController {

	private final SurveyService surveyService;
	
	
	@PostMapping("/survey")
	public ResponseEntity<SurveyDto> createSurvey(@RequestBody SurveyDto surveyDto) {
		System.out.println(">>>>>>>>>>>>>>>>"+surveyDto);
		SurveyDto createSurvey= surveyService.createSurvey(surveyDto);
		return ResponseEntity.ok(surveyDto);
	}
	
	@DeleteMapping("/survey/{id}")
	public ResponseEntity<?> deleteSurvey(@PathVariable(name = "id") Long id){
		surveyService.deleteSurvey(id);
		return ResponseEntity.ok("삭제성공");
				}
	
}
