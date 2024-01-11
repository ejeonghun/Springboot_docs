package com.example.myschool.lecture.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myschool.lecture.dto.LectureDto;
import com.example.myschool.lecture.service.LectureService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class LectureController {
	private final LectureService lectureService;
	
	@GetMapping("/lectures")
	public List<LectureDto> getLectures(
			@RequestParam(name="title", required=false) String title) {
			return lectureService.getLectures(title);
		}
	
	@GetMapping("/lectures/{id}")
	public LectureDto getLecture(@PathVariable("id") int id) {
		return lectureService.getLecture(id);
		}
	
}
