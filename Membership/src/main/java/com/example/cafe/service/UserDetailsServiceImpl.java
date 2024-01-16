package com.example.cafe.service;

import java.util.Optional;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cafe.entity.UserEntity;
import com.example.cafe.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> result = userRepository.findById(username);
		if(result.isEmpty())
			throw new UsernameNotFoundException("Invalid username");
		
		UserEntity user = result.get();
		return org.springframework.security.core.userdetails.User
				.withUsername(username)
				.password(user.getPassword())
				.build();
	}

}
