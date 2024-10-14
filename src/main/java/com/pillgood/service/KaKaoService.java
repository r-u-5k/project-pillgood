package com.pillgood.service;

import java.io.IOException;
import java.net.URI;

import com.pillgood.dto.KakaoProfile;
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
import com.pillgood.dto.NaverProfile;
import com.pillgood.dto.UserDto;
import com.pillgood.entity.User;
import com.pillgood.mapper.ToDtoMapper;
import com.pillgood.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class KaKaoService {
	/***************************************************
	 * 인가코드로 토큰받기
	 *****************************************************/
	@Value("${api.kakao.rest_api_key}")
	private String kakaoRestApiKey;

	@Value("${api.kakao.javascript_key}")
	private String kakaoJavascriptApiKey;

	@Value("${api.kakao.redirect_url}")
	private String redirect_url;

	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	public JSONObject getToken(String code) throws IOException {
		String kakaoAuthUrl = "https://kauth.kakao.com";
		String reqUrl = "/oauth/token";
		String accessToken = "";

		RestTemplate restTemplate = new RestTemplate();
		URI uri = URI.create(kakaoAuthUrl + reqUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Accept", "application/json");
		MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
		parameters.set("grant_type", "authorization_code");
		parameters.set("client_id", kakaoRestApiKey);
		parameters.set("redirect_uri", redirect_url);
		parameters.set("code", code);
		HttpEntity<MultiValueMap<String, Object>> restRequest = new HttpEntity<>(parameters, headers);
		//http요청
		ResponseEntity<JSONObject> apiResponse = restTemplate.postForEntity(uri, restRequest, JSONObject.class);
		JSONObject responseBody = apiResponse.getBody();
		return responseBody;
	}

	/*********************************************
	 * 카카오아이디얻기
	 *********************************************/
	public String getKakaoId(String accessToken) throws Exception {
		String kakaoUniqueNo = "";
		String kakaoApiUrl = "https://kapi.kakao.com";
		// restTemplate을 사용하여 API 호출
		RestTemplate restTemplate = new RestTemplate();
		String reqUrl = "/v2/user/me";
		URI uri = URI.create(kakaoApiUrl + reqUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "bearer " + accessToken);
		headers.set("KakaoAK", kakaoRestApiKey);

		MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();

		HttpEntity<MultiValueMap<String, Object>> restRequest = new HttpEntity<>(parameters, headers);
		ResponseEntity<JSONObject> apiResponse = restTemplate.postForEntity(uri, restRequest, JSONObject.class);
		JSONObject responseBody = apiResponse.getBody();
		kakaoUniqueNo = (Long) responseBody.get("id") + "";
		return kakaoUniqueNo;
	}

	/*********************************************
	 * 카카오유저정보얻기
	 *********************************************/
	public KakaoProfile getKakaoProfile(String accessToken) throws Exception {
		String kakaoApiUrl = "https://kapi.kakao.com";

		RestTemplate restTemplate = new RestTemplate();
		String reqUrl = "/v2/user/me";
		URI uri = URI.create(kakaoApiUrl + reqUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "bearer " + accessToken);
		headers.set("KakaoAK", kakaoRestApiKey);
		MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(parameters, headers);
		ResponseEntity<String> kakaoResponseEntity = restTemplate.postForEntity(uri, entity, String.class);

		String kakaoProfileStr = kakaoResponseEntity.getBody();
		System.out.println(">>12313>" + kakaoProfileStr);

		ObjectMapper objectMapper = new ObjectMapper();
		KakaoProfile kakaoProfile = objectMapper.readValue(kakaoProfileStr, KakaoProfile.class);
		/*	System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
			System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
		*/
		/*****************
		 * { "id":2717242759, "connected_at":"2023-03-22T06:20:29Z", "kakao_account":{
		 * "profile_nickname_needs_agreement":false,
		 * "profile_image_needs_agreement":false, "profile":{ "nickname":"김경호",
		 * "thumbnail_image_url":"http:\/\/k.kakaocdn.net\/dn\/dRK0M7\/btrYsai9oLx\/xXgccXzE3jBZfDEryf9Z8K\/img_110x110.jpg",
		 * "profile_image_url":"http:\/\/k.kakaocdn.net\/dn\/dRK0M7\/btrYsai9oLx\/xXgccXzE3jBZfDEryf9Z8K\/img_640x640.jpg",
		 * "is_default_image":false }, "has_email":true, "email_needs_agreement":false,
		 * "is_email_valid":true, "is_email_verified":true,
		 * "email":"guard884@gmail.com"}, "properties":{ "nickname":"김경호",
		 * "profile_image":"http:\/\/k.kakaocdn.net\/dn\/dRK0M7\/btrYsai9oLx\/xXgccXzE3jBZfDEryf9Z8K\/img_640x640.jpg"
		 * ,"thumbnail_image":"http:\/\/k.kakaocdn.net\/dn\/dRK0M7\/btrYsai9oLx\/xXgccXzE3jBZfDEryf9Z8K\/img_110x110.jpg"
		 * } }
		 ***********************/
		return kakaoProfile;
	}

	public UserDto saveUser(KakaoProfile kakaoProfile, String code) throws IOException {
		// 이메일을 기준으로 데이터베이스에서 사용자 정보를 찾습니다.
		String email = kakaoProfile.getKakao_account().getEmail();
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
			newUser.setName(kakaoProfile.getKakao_account().getProfile().getNickname());
			newUser.setGender(kakaoProfile.getKakao_account().getGender());
			newUser.setProvider("KAKAO");
			newUser.setPhone(kakaoProfile.getKakao_account().getPhone_number());
			newUser.setRole("USER");
			newUser.setToken(token);
			User savedUser = userRepository.save(newUser);
			return ToDtoMapper.toUserDto(savedUser);
			
			
		}
	}

}
