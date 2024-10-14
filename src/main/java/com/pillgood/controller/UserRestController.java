package com.pillgood.controller;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pillgood.dto.UserDto;
import com.pillgood.entity.User;
import com.pillgood.exception.UserNotFoundException;
import com.pillgood.service.EmailService;
import com.pillgood.service.MailService;
import com.pillgood.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserRestController {

	private final UserService userService;
	private final EmailService emailService;
	private final MailService mailService;

	@Operation(description = "회원가입", summary = "회원가입")
	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody UserDto.JoinDto joinDto) {
		UserDto userDto=userService.join(joinDto);
		if(userDto==null) {
			 return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("가입성공");
	}

	@Operation(description = "로그인", summary = "로그인")
	@PostMapping("/login/token")
	public ResponseEntity<?> login(@RequestBody UserDto.LoginDto loginDto, HttpServletResponse response)
			throws Exception {
		try {
			UserDto user = userService.login(loginDto.getEmail(), loginDto.getPassword());
			return ResponseEntity.ok().body(Map.of("message", "로그인 성공", "loginUser", user));
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "로그인 실패: " + e.getMessage()));
		}
	}

	@Operation(description = "아이디찾기", summary = "아이디찾기")
	@GetMapping("/findid/{phone}")
	public ResponseEntity<?> findId(
			@PathVariable(name = "phone") String phone) {
		try {
			String email = userService.findEmail(phone);
			if (email != null) {
				return ResponseEntity.ok(email); // 이메일 반환
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 사용자 이메일을 찾을 수 없습니다.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
		}
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable(name = "userId") Long userId) {
		UserDto userDto = userService.findOne(userId);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@Operation(description = "유저 정보 수정", summary = "유저 정보 수정")
	@PutMapping("/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable(name = "userId") Long userId,
			@RequestBody UserDto.UpdateDto updateDto) {
		try {
			UserDto user = userService.updateUser(updateDto);
			return ResponseEntity.ok().body(Map.of("message", "유저수정완료", "updateUser", user));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("유저 정보 수정 중 오류가 발생했습니다.");
		}
	}

	@Operation(description = "유저 삭제", summary = "유저 삭제")
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable(name = "userId") Long userId) {
		try {
			userService.deleteUser(userId);
			return ResponseEntity.ok("삭제성공");
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 사용자를 찾을 수 없습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 탈퇴 중 오류가 발생했습니다.");
		}
	}

	/*@Operation(description = "토큰검증", summary = "토큰검증")
	@GetMapping("/user/validate")
	public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token) {
		// 클라이언트에서 전송된 토큰 유효성 검증
		if (jwtTokenProvider.validateToken(token)) {
			return ResponseEntity.ok("Valid token");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
		}
	}*/
	
	@Operation(summary = "회원비밀번호찾기")
	@GetMapping(value = "/findpassword/{id}")
	public ResponseEntity<String> findUserPassword(@PathVariable("id") String id){
		String tempPassword = userService.updateUserPassword(id);
		String body="회원님의 임시 비밀번호 >> "+tempPassword;
		mailService.sendEmail(id, body);
		
		return ResponseEntity.status(HttpStatus.OK).body("임시 비밀번호 전송 성공!"); 
	}
	
	@Operation(description = "인증코드발송", summary = "이메일인증코드발송")
	@PostMapping("/sendcode")
	public ResponseEntity<?> sendVerificationCode(@RequestBody String email) {
		try {
			String verificationCode = emailService.generateVerificationCode(email);
			if (verificationCode != null) {
				// 이메일 발송 로직 (실제 이메일 서비스를 통해 발송)
				emailService.sendEmail(email, "필굿인증", "Verification code: " + verificationCode);
				return ResponseEntity.ok("인증번호가 발송되었습니다.");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("등록되지 않은 이메일입니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("인증번호 발송 중 오류가 발생했습니다.");
		}
	}

	@PostMapping("/verificationcode")
	public ResponseEntity<?> checkCode(@RequestBody UserDto.CodeCheckDto checkDto) {
		User user = emailService.checkVerificationCode(checkDto.getEmail(), checkDto.getCode());
		if (user != null) {
			return ResponseEntity.ok("인증확인" + user.getPassword());
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증실패");
		}
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<String> handleIllegalStateException(IllegalStateException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
}
