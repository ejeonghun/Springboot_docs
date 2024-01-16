package com.example.Memos.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Memos.dto.AddMemoRequest;
import com.example.Memos.dto.AddUserRequest;
import com.example.Memos.dto.AddUserResponse;
import com.example.Memos.service.MemoService;
import com.example.Memos.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class WebController {
	private final UserService userService;
	private final MemoService memoService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/signin")
	public String signIn(@RequestParam(name = "message", required = false) String message, Model model) {
		model.addAttribute("message", message);
		return "signin";
	}

	@GetMapping("/join")
	public String join() {
		return "join";
	}

	@PostMapping("/join")
	public String postJoin(@RequestParam("username") String username, @RequestParam("password") String password,
			RedirectAttributes ra) {
		AddUserResponse result = userService.addUser(new AddUserRequest(username, password));
		ra.addAttribute("message", "User Added. Please Sign in.");
		return "redirect:/signin";
	}

	@GetMapping("/memos")
	public String memos(Model model, Principal user) {
		model.addAttribute("memos", memoService.getMemosByUser(user.getName()));
		return "memos";
	}
	
	@PostMapping("/memos")
	public String addMemo(@RequestParam("body") String body, Principal user) {
		memoService.addMemo(new AddMemoRequest(null, body, null, user.getName()));
		return "redirect:/memos";	
		}
	
	@PostMapping("/memos/delete/{id}")
	public String deleteMemo(
			@PathVariable("id") Long id) {
		memoService.deleteMemo(id);
		return "redirect:/memos";
	}
}