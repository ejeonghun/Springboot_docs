package com.example.cafe.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cafe.dto.UserDto;
import com.example.cafe.entity.UserEntity;
import com.example.cafe.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bcryptPasswordEncoder;
	
	private UserEntity requestToEntity(UserDto dto) {
		String password = bcryptPasswordEncoder.encode(dto.getPassword()); // 암호화
		return new UserEntity(dto.getUsername(), password);
	}
	
	public String addUser(String username, String password) { // 회원가입
		userRepository.save(requestToEntity(new UserDto(username, password)));
		return "유저 생성 완료";
	}
}
