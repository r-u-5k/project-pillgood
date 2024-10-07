package com.pillgood.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.pillgood.config.filter.CustomAccessDeniedHandler;
import com.pillgood.config.filter.CustomAuthenticationEntryPoint;
import com.pillgood.config.filter.JwtAuthenticationFilter;
import com.pillgood.config.filter.JwtTokenProvider;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

/**
 * 어플리케이션의 보안 설정
 */
@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {
    /**
     * Swagger 페이지 접근에 대한 예외 처리
     *
     * @param webSecurity
     */
    public static final String[] SwaggerPatterns = { "/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs/**",
            "/v3/api-docs", "/swagger-ui.html" };
    @Lazy
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((t) -> {
            t.anyRequest().permitAll();
        });
        httpSecurity.httpBasic((httpBasicConfig) -> {
            // REST API는 UI를 사용하지 않으므로 기본설정을 비활성화
            httpBasicConfig.disable();
        });
        httpSecurity.csrf((csrfConfig) -> {
            // REST API는 csrf 보안이 필요 없으므로 비활성화
            csrfConfig.disable();
        });
        httpSecurity.sessionManagement((sessionManagementConfig) -> {
            // JWT Token 인증방식으로 세션은 필요 없으므로 비활성화
            sessionManagementConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
		/*httpSecurity.authorizeHttpRequests((authorizeHttpRequestsConfig) -> {
			//swagger설정
			authorizeHttpRequestsConfig.requestMatchers(SwaggerPatterns).permitAll();
			// 가입 및 로그인 주소는 허용
			authorizeHttpRequestsConfig.requestMatchers("/api/user/join", "/api/user/exception","api/user/login/token").permitAll();
			authorizeHttpRequestsConfig.requestMatchers("/api/user/exception","/api/user/**").hasAnyRole("USER");
			// product로 시작하는 Get 요청은 허용
			authorizeHttpRequestsConfig.requestMatchers(HttpMethod.GET, "/product/**").permitAll();
			authorizeHttpRequestsConfig.requestMatchers("**exception**").permitAll();
			// 나머지 요청은 인증된 ADMIN만 접근 가능
			authorizeHttpRequestsConfig.anyRequest().hasRole("USER");
		});*/


        httpSecurity.exceptionHandling((exceptionHandlingConfig) -> {
            exceptionHandlingConfig.accessDeniedHandler(new CustomAccessDeniedHandler());
            exceptionHandlingConfig.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
        });
        // JWT Token 필터를 id/password 인증 필터 이전에 추가
        httpSecurity.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter.class);
        httpSecurity
                .cors((corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration configuration = new CorsConfiguration();

                        configuration.setAllowedOrigins(Collections.singletonList("http://192.168.15.3:5173"));
                        configuration.setAllowedMethods(Collections.singletonList("*"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setMaxAge(3600L);

                        configuration.setExposedHeaders(Collections.singletonList("Authorization"));

                        return configuration;
                    }
                })));

        return httpSecurity.build();
    }

    /**
     * Swagger 페이지 접근에 대한 예외 처리
     *
     * @param webSecurity
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (webSecurity) -> {
            webSecurity.ignoring().requestMatchers(SwaggerPatterns);
            webSecurity.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        };
    }

}