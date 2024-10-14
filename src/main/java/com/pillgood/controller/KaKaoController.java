package com.pillgood.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pillgood.dto.KakaoProfile;
import com.pillgood.dto.UserDto;
import com.pillgood.service.KaKaoService;
import com.pillgood.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = "http://192.168.15.3:5173")
@Controller
public class KaKaoController {
	@Value("${api.kakao.rest_api_key}")
	private String client_id;

	@Value("${api.kakao.redirect_url}")
	private String redirect_url;

	@Value("${api.kakao.logout_redirect_uri}")
	private String logout_redirect_uri;
	@Autowired
	private KaKaoService kakaoService;
	@Autowired
	private UserService userService;

	@GetMapping("/kakao_login")
	public String kakaoLogin() {
		// 카카오 로그인 URL로 리다이렉트
		return "redirect:https://kauth.kakao.com/oauth/authorize?client_id=d2d11f66bbfdfde21c72cb215e3985c0&redirect_uri=http://192.168.15.3:8080/kakao_login_action&response_type=code";
	}

	@GetMapping(value = "/kakao_login_action")
	public String kakao_login_action(@RequestParam(value = "code", required = false) String code,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> userInfo = new HashMap<>();
		JSONObject tokenObject = kakaoService.getToken(code);
		String authorize_access_token = (String) tokenObject.get("access_token");
		int refresh_token_expires_in = (Integer) tokenObject.get("refresh_token_expires_in");
		String refresh_token = (String) tokenObject.get("refresh_token");

		KakaoProfile kakaoProfile = kakaoService.getKakaoProfile(authorize_access_token);
		UserDto kakaoUser = kakaoService.saveUser(kakaoProfile, authorize_access_token);
		System.out.println(">>>>>>>>>>>" + kakaoUser);
		Cookie authorize_access_token_cookie = new Cookie("authorize_access_token", authorize_access_token);
		Cookie refresh_token_expires_in_cookie = new Cookie("refresh_token_expires_in", refresh_token_expires_in + "");
		Cookie refresh_token_cookie = new Cookie("refresh_token", refresh_token);
		response.addCookie(authorize_access_token_cookie);
		response.addCookie(refresh_token_expires_in_cookie);
		response.addCookie(refresh_token_cookie);
		request.setAttribute("kakaoProfile", kakaoProfile);
		request.setAttribute("authorize_access_token", authorize_access_token);
		request.setAttribute("refresh_token_expires_in", refresh_token_expires_in);
		request.setAttribute("refresh_token", refresh_token);
		request.setAttribute("client_id", client_id);
		request.setAttribute("redirect_url", redirect_url);
		request.setAttribute("logout_redirect_uri", logout_redirect_uri);
		return "redirect:http://192.168.15.3:5173";
	}
	@ResponseBody
	@GetMapping("/api/kakao_userinfo_with_token")
	public UserDto getKakaoUserInfoWithToken(
			@RequestParam(name = "authorize_access_token") String authorize_access_token, HttpSession session)
			throws Exception {
		System.out.println(authorize_access_token);
		KakaoProfile kakaoProfile = kakaoService.getKakaoProfile(authorize_access_token);
		UserDto kakaoUser = userService.findUserByEmail(kakaoProfile.getKakao_account().getEmail());

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>요청성공");
		return kakaoUser;

	}
}