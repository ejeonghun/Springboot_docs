package com.example.Memos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Memos.domain.Memo;

public interface MemoRepositroy extends JpaRepository<Memo, Long> {
	public List<Memo> findByUser_usernameOrderByCreatedAtDesc(String username);

}
