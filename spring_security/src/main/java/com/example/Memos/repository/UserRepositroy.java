package com.example.Memos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Memos.domain.User;

public interface UserRepositroy extends JpaRepository<User, String> {
	
}
