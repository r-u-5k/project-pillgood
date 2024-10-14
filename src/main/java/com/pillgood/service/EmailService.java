package com.pillgood.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.pillgood.entity.User;
import com.pillgood.entity.VerificationCode;
import com.pillgood.repository.UserRepository;
import com.pillgood.repository.VerificationCodeRepository;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    public void sendEmail(String email, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tnghks7915@naver.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        try {
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error sending email: " + e.getMessage());
        }
    }
  //인증번호 생성
  	public String generateVerificationCode(String email) {
  		User user = userRepository.findByEmailAndDeleted(email,false);
  		if (user == null) {
  			throw new IllegalArgumentException("등록되지 않은 이메일입니다.");
  		}
  		String code = UUID.randomUUID().toString().substring(0, 6);
  		VerificationCode verificationCode = new VerificationCode(email,code );
  		verificationCodeRepository.save(verificationCode);
  		return code;
  	}
  	
  	//인증번호 검증
  	public User checkVerificationCode(String email, String code) throws IllegalArgumentException {
  		VerificationCode verificationCode = verificationCodeRepository.findByEmail(email);
  		
  		if (verificationCode != null && verificationCode.getCode().equalsIgnoreCase(code)) {
  			if (verificationCode.getExpiryDate().isAfter(LocalDateTime.now())) {
  				// 코드가 유효하면 관련된 사용자 정보를 반환
  				return userRepository.findByEmailAndDeleted(email,false);
  			} else {
  				// 코드가 만료된 경우
  				throw new IllegalArgumentException("인증 시간이 만료되었습니다.");
  			}
  		} else {
  			// 코드가 일치하지 않는 경우
  			throw new IllegalArgumentException("잘못된 인증 코드입니다.");
  		}
  	}

    	
}
