package com.example.Memos.service;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;

import com.example.Memos.domain.Memo;
import com.example.Memos.domain.User;
import com.example.Memos.dto.AddMemoRequest;
import com.example.Memos.dto.GetMemoResponse;
import com.example.Memos.repository.MemoRepositroy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemoService {
	
	private final MemoRepositroy memoRepository;
	
	private GetMemoResponse entityToResponse(Memo memo) {
		return new GetMemoResponse(
				memo.getId(),
				memo.getBody(),
				memo.getCreatedAt(),
				memo.getUser().getUsername()
				);
	}
	
	private Memo requestToEntity(AddMemoRequest dto) {
		User user = new User();
		user.setUsername(dto.getUsername());
		return new Memo(
				null,
				dto.getBody(),
				null,
				user);
	}
	
	public List<GetMemoResponse> getMemosByUser(String username) {
		return memoRepository.findByUser_usernameOrderByCreatedAtDesc(username).stream()
				.map(memo-> entityToResponse(memo))
				.collect(Collectors.toList());
		
	}
	
	public Long addMemo(AddMemoRequest dto) {
		Memo result = memoRepository.save(requestToEntity(dto));
		return result.getId();
	}
	
	public void deleteMemo(Long id) {
		memoRepository.deleteById(id);
	}
	
}
