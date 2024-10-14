/*package com.pillgood.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pillgood.dto.KakaoProfile;
import com.pillgood.dto.NaverLoginProfile;
import com.pillgood.dto.UserDto;
import com.pillgood.service.NaverService2;
import com.pillgood.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class NaverRestController {

	@Value("${api.naver.client_id}")
	private String client_id;
	
	@Value("${api.naver.redirect_url}")
	private String redirect_url;
	
	@Value("${api.naver.client_secret}")
	private String secret;
	
	@Autowired
	private NaverService2 naverService2;
	
	@Autowired
	private UserService userService;
   // kakao_main.html 페이지보여주기
    


	
	@GetMapping(value = "/naver_login_action")
	public String  naver_login_action(@RequestParam(value = "code", required = false) String code,@RequestParam(value = "state",required = false) String state,
	HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String, Object> userInfo = new HashMap<>();
		JSONObject tokenObject= naverService2.getToken(code,state);
		System.out.println(tokenObject+"나는토큰오브젝트");
		String authorize_access_token=(String)tokenObject.get("access_token");
		String refresh_token_expires_in=(String)tokenObject.get("expires_in");
		String refresh_token=(String)tokenObject.get("refresh_token");
		NaverLoginProfile naverLoginProfile = naverService2.getNaverProfile(authorize_access_token);
   		System.out.println(naverLoginProfile+"나는네이버프로필~~~~~~~~~");
		UserDto naverUser = naverService2.saveUser(naverLoginProfile,authorize_access_token);
   		System.out.println(">>>>>>>>>>>"+naverUser);
         Cookie authorize_access_token_cookie=new Cookie("authorize_access_token", authorize_access_token);
         Cookie refresh_token_expires_in_cookie=new Cookie("refresh_token_expires_in", refresh_token_expires_in+"");
         Cookie refresh_token_cookie=new Cookie("refresh_token", refresh_token);
         response.addCookie(authorize_access_token_cookie);
         response.addCookie(refresh_token_expires_in_cookie);
         response.addCookie(refresh_token_cookie);
         request.setAttribute("naverProfile", naverLoginProfile);
         request.setAttribute("authorize_access_token", authorize_access_token);
         request.setAttribute("refresh_token_expires_in", refresh_token_expires_in);
         request.setAttribute("refresh_token", refresh_token);
         request.setAttribute("client_id",client_id);
         request.setAttribute("redirect_url",redirect_url);
         return "redirect:http://192.168.15.3:5173";
	}	
	
	
	@ResponseBody
	@GetMapping("/api/naver_userinfo_with_token")
	public UserDto getNaverUserInfoWithToken(@RequestParam(name ="authorize_access_token" )  String authorize_access_token, HttpSession session) throws Exception {
		System.out.println(authorize_access_token);
		NaverLoginProfile naverProfile = naverService2.getNaverProfile(authorize_access_token);
		UserDto naverUser=userService.findUserByEmail(naverProfile.getResponse().getEmail());
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>요청성공");
		return naverUser;
		
		
	}
}
*/