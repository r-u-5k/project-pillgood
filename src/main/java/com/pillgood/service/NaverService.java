package com.pillgood.service;

import com.pillgood.config.filter.JwtTokenProvider;
import com.pillgood.dto.NaverProfile;
import com.pillgood.dto.UserDto;
import com.pillgood.entity.User;
import com.pillgood.mapper.ToDtoMapper;
import com.pillgood.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;

@Transactional
@RequiredArgsConstructor
@Service
public class NaverService {
    @Value("${api.naver.client_id}")
    private String naverClientId;

    @Value("${api.naver.client_secret}")
    private String naverClientSecret;

    @Autowired
    private RestTemplate restTemplate;

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public Map<String, Object> getToken(String code, String state) {
        String naverTokenUrl = "https://nid.naver.com/oauth2.0/token";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(naverTokenUrl)
                .queryParam("grant_type", "authorization_code")
                .queryParam("client_id", naverClientId)
                .queryParam("client_secret", naverClientSecret)
                .queryParam("code", code)
                .queryParam("state", state);

        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(uriBuilder.toUriString(), null, Map.class);
        return responseEntity.getBody();
    }

    public NaverProfile getNaverProfile(String accessToken) throws Exception {
        String naverProfileUrl = "https://openapi.naver.com/v1/nid/me";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<NaverProfile> responseEntity = restTemplate.exchange(naverProfileUrl, HttpMethod.GET, entity, NaverProfile.class);
        return responseEntity.getBody();
    }

    public UserDto saveUser(NaverProfile naverProfile) throws IOException {
        String email = naverProfile.getResponse().getEmail();
        User existingUser = userRepository.findByEmailAndDeleted(email, false);
        if (existingUser != null) {
            String token = jwtTokenProvider.createToken(email, "USER");
            existingUser.setToken(token);
            return ToDtoMapper.toUserDto(existingUser);
        } else {
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
}
