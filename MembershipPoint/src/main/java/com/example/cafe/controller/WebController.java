package com.example.cafe.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.cafe.dto.PointDto;
import com.example.cafe.service.PointService;
import com.example.cafe.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class WebController {
	private final UserService userService;
	private final PointService pointService;

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
		userService.addUser(username, password);
		ra.addAttribute("message", "User Added. Please Sign in.");
		return "redirect:/signin";
	}

	@GetMapping("/points")
	public String points(Model model, Principal user) {
		return "points";
	}
	
	@PostMapping("/points")
	public String point(Model model,@RequestParam("phone") String phone) {
		model.addAttribute("points", pointService.SearchPhone(phone));
		return "points";
	}

	@PostMapping("/points/{phone}")
	public String ChangePoint(
			Model model,
			@PathVariable("phone") String phone,
			@RequestParam("point") Long point){
		PointDto res = pointService.ChangePoint(new PointDto(phone,point));
		model.addAttribute("points", res);
		return "points";
	}
}
