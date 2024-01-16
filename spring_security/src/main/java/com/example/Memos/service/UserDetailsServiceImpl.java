package com.example.Memos.service;

import java.util.Optional;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Memos.domain.User;
import com.example.Memos.repository.UserRepositroy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserRepositroy userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> result = userRepository.findById(username);
		if(result.isEmpty())
			throw new UsernameNotFoundException("Invalid username");
		
		User user = result.get();
		return org.springframework.security.core.userdetails.User
				.withUsername(username)
				.password(user.getPassword())
				.roles(user.getRole())
				.build();
	}

}
