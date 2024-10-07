package com.pillgood.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pillgood.dto.SurveyDto;
import com.pillgood.entity.Survey;
import com.pillgood.entity.User;
import com.pillgood.exception.UserNotFoundException;
import com.pillgood.repository.SurveyRepository;
import com.pillgood.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class SurveyService {

	private final SurveyRepository surveyRepository;
	private final UserRepository userRepository;
	//설문 생성
	public SurveyDto createSurvey(SurveyDto surveyDto) {
		User user=userRepository.findById(surveyDto.getUserId()).orElseThrow(()->new UserNotFoundException("유저가없습니다"));
		
		Survey survey  = new Survey();
		
		survey.setUser(user);
		survey.setHeight(surveyDto.getHeight());
		survey.setKey(surveyDto.getKey());
		survey.setSymptomType(surveyDto.getSymptomType());
		survey.setWeight(surveyDto.getWeight());
		
		Survey saveSurvey=surveyRepository.save(survey);
		
		return SurveyDto.builder()
		.height(saveSurvey.getHeight())
		.key(saveSurvey.getKey())
		.id(saveSurvey.getNo())
		.symptomType(saveSurvey.getSymptomType())
		.userId(saveSurvey.getUser().getId())
		.weight(saveSurvey.getWeight())
		.build();
	}
	
	//설문 삭제
	
	public void deleteSurvey(Long id) {
		surveyRepository.deleteById(id);
	}
	//
}
