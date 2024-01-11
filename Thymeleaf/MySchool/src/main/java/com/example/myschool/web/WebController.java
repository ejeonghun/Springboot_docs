package com.example.myschool.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.myschool.lecture.service.LectureService;

import com.example.myschool.teacher.service.TeacherService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class WebController {
	private final TeacherService teacherService;
	private final LectureService lectureService;

	
	
	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/view-teachers")
	public String teachers(Model model) {
		model.addAttribute("teachers", teacherService.getTeachers(null));
				return "teachers";
	}
	
	
	@GetMapping("/view-lectures")
	public String lectures(Model model) {
		model.addAttribute("lectures", lectureService.getLectures(null));
		return "lectures";
	}
	
	@GetMapping("/view-teachers/{id}")
	public String teacher(Model model, @PathVariable("id") int id){
		model.addAttribute("teacher", teacherService.getTeacher(id));
		model.addAttribute("lectures", lectureService.getTeacherLecture(id));
		return "teacher-info";
	}
}