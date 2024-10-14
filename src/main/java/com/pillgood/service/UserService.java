package com.pillgood.service;

import java.security.SecureRandom;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pillgood.config.filter.JwtTokenProvider;
import com.pillgood.dto.UserDto;
import com.pillgood.entity.User;
import com.pillgood.exception.PasswordMismatchException;
import com.pillgood.exception.UserNotFoundException;
import com.pillgood.mapper.ToDtoMapper;
import com.pillgood.repository.UserRepository;
import com.pillgood.repository.VerificationCodeRepository;

import lombok.Data;

@Transactional
@Data
@Service
public class UserService {

	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final VerificationCodeRepository verificationCodeRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final JwtTokenProvider jwtTokenProvider;

	//회원가입
	public UserDto join(UserDto.JoinDto joinDto) {
		//중복회원방지
		validateDuplicateUser(joinDto);
		// 비밀번호 암호화
		String encryptedPassword = bCryptPasswordEncoder.encode(joinDto.getPassword());
		// Entity 저장
		User user = new User();
		user.setRole("USER");
		user.setBirthday(joinDto.getBirthday());
		user.setEmail(joinDto.getEmail().toLowerCase());
		user.setGender(joinDto.getGender());
		user.setPassword(encryptedPassword);
		user.setPhone(joinDto.getPhone());
		user.setName(joinDto.getName());
		User saveUser = userRepository.save(user);
		return ToDtoMapper.toUserDto(saveUser);
	}

	//회원가입 중복 방지
	private void validateDuplicateUser(UserDto.JoinDto joinDto) {
		User findUser = userRepository.findByEmailAndDeleted(joinDto.getEmail().toLowerCase(),false);
		if (findUser != null) {
			throw new IllegalStateException("이미존재하는회원입니다.");
		}
	}

	//회원수정
	public UserDto updateUser(UserDto.UpdateDto userDto) {
		User user = userRepository.findById(userDto.getId()).orElseThrow(() -> new UserNotFoundException("유저정보가 없습니다"));
		System.out.println(userDto+"alkjdaksdjlaskjdlaksdjlaksjdlaksdjlaksdjlaksjdalksdjlaksdjlaksjd");
		boolean isPasswordMatch = bCryptPasswordEncoder.matches(userDto.getOldPassword(), user.getPassword());
		if (!isPasswordMatch) {
			throw new PasswordMismatchException("패스워드가 일치하지 않습니다.");
		}
		
		if(userDto.getPassword()==null) {
			throw new PasswordMismatchException("비밀번호가 비었다");
		}
		
		String encryptedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());
		user.setPassword(encryptedPassword);
		User updateUser = userRepository.save(user);
		System.out.println(updateUser + "<<<<<<<<<<<<<<<<<<<<<");
		return ToDtoMapper.toUserDto(updateUser);
	}

	//로그인
	public UserDto login(String email, String password) throws Exception {
		User loginUser = userRepository.findByEmailAndDeleted(email,false);
		if (loginUser == null) {
			throw new UserNotFoundException("아이디 또는 비밀번호를 확인해주세요");
		}

		// 입력된 비밀번호를 암호화하여 저장된 비밀번호와 비교
		boolean isPasswordMatch = bCryptPasswordEncoder.matches(password, loginUser.getPassword());
		if (!isPasswordMatch) {
			throw new PasswordMismatchException("패스워드가 일치하지 않습니다.");
		}
		String role = loginUser.getRole();
		String token = jwtTokenProvider.createToken(email, role);
		loginUser.setToken(token);
		UserDto loginDtoUser = ToDtoMapper.toUserDto(loginUser);
		return loginDtoUser;
	}

	//회원 한명 조회
	public UserDto findOne(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("해당 사용자를 찾을 수 없습니다."));
		return ToDtoMapper.toUserDto(user);
	}
	
	//회원 이메일로 조회
		public UserDto findUserByEmail(String email) {
			User user = userRepository.findByEmailAndDeleted(email,false);
			return ToDtoMapper.toUserDto(user);
		}

	//회원전체조회
	public List<User> findAll() throws Exception {
		return userRepository.findAll();
	}

	//회원탈퇴
	public void deleteUser(Long userId) throws UserNotFoundException {
		User deleteUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("해당 사용자를 찾을 수 없습니다."));
		deleteUser.setDeleted(true);
		userRepository.save(deleteUser);
	}

	//아이디찾기
	public String findEmail(String phone) throws UserNotFoundException {
		User findUser = userRepository.findByPhone(phone);
		if (findUser == null) {
			throw new UserNotFoundException("해당 전화번호를 가진 사용자를 찾을 수 없습니다.");
		}
		return findUser.getEmail();
	}

	//비밀번호찾기
	public String findPassword(String email, String name) {
		User findUser = userRepository.findByEmailAndName(email, name);
		return findUser.getPassword();
	}
	
	//비밀번호 임시비밀번호로 변경
		public String updateUserPassword(String email) {
			User user = userRepository.findByEmail(email);
			int tempPassword=createTempPassword();
			user.setPassword(bCryptPasswordEncoder.encode(tempPassword+""));
			userRepository.save(user);
			return tempPassword+"";
		}
		
		//임시비밀번호생성
		public int createTempPassword() {
		        SecureRandom secureRandom = new SecureRandom();
		        int upperLimit = (int) Math.pow(10,10);
		        return secureRandom.nextInt(upperLimit);
		}
}
