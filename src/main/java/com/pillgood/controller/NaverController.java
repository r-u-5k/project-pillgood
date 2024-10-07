package com.pillgood.controller;

import java.util.HashMap;
import java.util.Map;

import com.pillgood.service.NaverService;
import com.pillgood.config.filter.JwtTokenProvider;  // JwtTokenProvider import

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pillgood.dto.NaverProfile;
import com.pillgood.dto.UserDto;
import com.pillgood.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://192.168.15.3:5173", allowCredentials = "true")
@Controller
public class NaverController {

    @Value("${api.naver.client_id}")
    private String client_id;

    @Value("${api.naver.redirect_url}")
    private String redirect_url;

    @Value("http://192.168.15.3:8080/naver_main")
    private String logout_redirect_uri;

    @Autowired
    private NaverService naverService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/naver_main")
    @ResponseBody
    public Map<String, String> naver_main() {
        Map<String, String> response = new HashMap<>();
        response.put("client_id", client_id);
        response.put("redirect_url", redirect_url);
        response.put("logout_redirect_uri", logout_redirect_uri);
        return response;
    }

    @GetMapping(value = "/naver_login_action")
    public String naver_login_action(@RequestParam("code") String code, @RequestParam("state") String state, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String storedState = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("naver_state".equals(cookie.getName())) {
                    storedState = cookie.getValue();
                    break;
                }
            }
        }
        if (!state.equals(storedState)) {
            throw new IllegalStateException("Invalid state parameter");
        }

        try {
            Map<String, Object> tokenObject = naverService.getToken(code, state);
            String authorize_access_token = (String) tokenObject.get("access_token");
            String refresh_token = (String) tokenObject.get("refresh_token");
            String expires_in_str = (String) tokenObject.get("expires_in");
            String refresh_token_expires_in = expires_in_str;

            NaverProfile naverProfile = naverService.getNaverProfile(authorize_access_token);
            UserDto naverUser = naverService.saveUser(naverProfile);

            // JWT 생성
            String jwtToken = jwtTokenProvider.createToken(naverUser.getEmail(), naverUser.getRole());

            // JWT 쿠키에 저장
            Cookie jwtCookie = new Cookie("jwt_token", jwtToken);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setPath("/");
            response.addCookie(jwtCookie);
            response.setHeader("Authorization", "Bearer " + jwtToken);

            Cookie authorize_access_token_cookie = new Cookie("authorize_access_token", authorize_access_token);
            Cookie refresh_token_expires_in_cookie = new Cookie("refresh_token_expires_in", refresh_token_expires_in + "");
            Cookie refresh_token_cookie = new Cookie("refresh_token", refresh_token);
            response.addCookie(authorize_access_token_cookie);
            response.addCookie(refresh_token_expires_in_cookie);
            response.addCookie(refresh_token_cookie);

            request.setAttribute("naverProfile", naverProfile);
            request.setAttribute("authorize_access_token", authorize_access_token);
            request.setAttribute("refresh_token_expires_in", refresh_token_expires_in);
            request.setAttribute("refresh_token", refresh_token);
            request.setAttribute("client_id", client_id);
            request.setAttribute("redirect_url", redirect_url);
            request.setAttribute("logout_redirect_uri", logout_redirect_uri);
            return "redirect:http://192.168.15.3:5173";
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @ResponseBody
    @GetMapping("/api/naver_userinfo_with_token")
    public UserDto getNaverUserInfoWithToken(@RequestParam(name = "authorize_access_token") String authorize_access_token) throws Exception {
        System.out.println("authorize_access_token = " + authorize_access_token);
        NaverProfile naverProfile = naverService.getNaverProfile(authorize_access_token);
        return userService.findUserByEmail(naverProfile.getResponse().getEmail());
    }
}
