/*package com.pillgood.service;

import java.io.IOException;
import java.net.URI;import java.util.Date;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pillgood.config.filter.JwtTokenProvider;
import com.pillgood.dto.KakaoProfile;
import com.pillgood.dto.NaverLoginProfile;
import com.pillgood.dto.UserDto;
import com.pillgood.entity.User;
import com.pillgood.mapper.ToDtoMapper;
import com.pillgood.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class NaverService2 {
	*//***************************************************
		* 인가코드로 토큰받기
		*****************************************************//*
																@Value("${api.naver.client_id}")
																private String naverClientId;
																
																@Value("${api.naver.client_secret}")
																private String secret;
																
																@Value("${api.kakao.redirect_url}")
																private String redirect_url;
																
																private final UserRepository userRepository;
																private final JwtTokenProvider jwtTokenProvider;
																
																public JSONObject getToken(String code, String state) throws IOException {
																String naverAuthUrl = "https://nid.naver.com";
																String reqUrl = "/oauth2.0/token";
																String accessToken = "";
																RestTemplate restTemplate = new RestTemplate();
																URI uri = URI.create(naverAuthUrl + reqUrl);
																HttpHeaders headers = new HttpHeaders();
																headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
																headers.add("Accept", "application/json");
																MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
																parameters.set("grant_type", "authorization_code");
																parameters.set("client_id", naverClientId);
																parameters.set("client_secret", secret);
																parameters.set("code", code);
																parameters.set("state", state);
																HttpEntity<MultiValueMap<String, Object>> restRequest = new HttpEntity<>(parameters, headers);
																//http요청
																ResponseEntity<JSONObject> apiResponse = restTemplate.postForEntity(uri, restRequest, JSONObject.class);
																JSONObject responseBody = apiResponse.getBody();
																return responseBody;
																}
																
																public UserDto saveUser(NaverLoginProfile naverProfile, String code) throws IOException {
																// 이메일을 기준으로 데이터베이스에서 사용자 정보를 찾습니다.
																String email = naverProfile.getResponse().getEmail();
																User existingUser = userRepository.findByEmailAndDeleted(email,false);
																// 이미 가입된 사용자인지 확인합니다.
																if (existingUser != null) {
																String token = jwtTokenProvider.createToken(email, "USER");
																existingUser.setToken(token);
																// 이미 가입된 사용자라면 아무런 작업을 하지 않고 기존 사용자 정보를 반환합니다.
																return ToDtoMapper.toUserDto(existingUser);
																} else {
																// 가입되지 않은 사용자라면 새로운 사용자 정보를 저장합니다.
																User newUser = new User();
																String token = jwtTokenProvider.createToken(email, "USER");
																newUser.setEmail(email);
																newUser.setName(naverProfile.getResponse().getName());
																newUser.setGender(naverProfile.getResponse().getGender());
																newUser.setProvider("NAVER");
																newUser.setPhone(naverProfile.getResponse().getMobile());
																newUser.setRole("USER");
																newUser.setToken(token);
																User savedUser = userRepository.save(newUser);
																return ToDtoMapper.toUserDto(savedUser);
																
																
																}
																}
																
																public NaverLoginProfile getNaverProfile(String authorize_access_token)throws Exception {
																String naverApiUrl = "https://openapi.naver.com/v1/nid/me";
																
																RestTemplate restTemplate = new RestTemplate();
																URI uri = URI.create(naverApiUrl);
																HttpHeaders headers = new HttpHeaders();
																headers.set("Authorization", "bearer " + authorize_access_token);
																MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
																HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(parameters, headers);
																ResponseEntity<String> naverResponseEntity = restTemplate.postForEntity(uri, entity, String.class);
																
																String naverProfileStr = naverResponseEntity.getBody();
																System.out.println(">>12313>" + naverProfileStr);
																
																ObjectMapper objectMapper = new ObjectMapper();
																NaverLoginProfile naverProfile = objectMapper.readValue(naverProfileStr, NaverLoginProfile.class);
																
																return naverProfile;
																}
																
																}
																*/