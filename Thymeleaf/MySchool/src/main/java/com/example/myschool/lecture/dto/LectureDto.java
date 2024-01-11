package com.example.myschool.lecture.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class LectureDto {
	private int lectureId;
	private String teacherId;
	private String title;
	private String descriptiton;
}
