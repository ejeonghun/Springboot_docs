package com.example.Memos.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Memos.domain.User;
import com.example.Memos.dto.AddUserRequest;
import com.example.Memos.dto.AddUserResponse;
import com.example.Memos.repository.UserRepositroy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepositroy userRepository;
	private final BCryptPasswordEncoder bcryptPasswordEncoder;
	
	private User requestToEntity(AddUserRequest dto) {
		String password = bcryptPasswordEncoder.encode(dto.getPassword()); // 암호화
		return new User(dto.getUsername(), password, "user");
	}
	
	private AddUserResponse entityToResponse(User user) {
		return new AddUserResponse(user.getUsername(), "ok");
	}
	
	public AddUserResponse addUser(AddUserRequest dto) {
		User result = userRepository.save(requestToEntity(dto));
		return entityToResponse(result);
	}
}
