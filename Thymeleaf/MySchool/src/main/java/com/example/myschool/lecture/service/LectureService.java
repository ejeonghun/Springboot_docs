package com.example.myschool.lecture.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.myschool.lecture.domain.Lecture;
import com.example.myschool.lecture.dto.LectureDto;
import com.example.myschool.lecture.repository.LectureRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LectureService {
	
	private final LectureRepository lectureRepository;
	
	private LectureDto entityToDto(Lecture lecture) {
		return LectureDto.builder()
				.lectureId(lecture.getLectureId())
				.descriptiton(lecture.getDescription())
				.title(lecture.getTitle())
				.teacherId(lecture.getTeacher().getName())
				.build();
	}
	
	public List<LectureDto> getLectures(String title) {
		ArrayList<LectureDto> lectures = new ArrayList<LectureDto>();
		
		if(title == null) {
			lectureRepository.findAll().forEach(lecture->{
				lectures.add(entityToDto(lecture));
			});
		} else {
			lectureRepository.findByTitleContainsIgnoreCase(title).forEach(lecture->{
				lectures.add(entityToDto(lecture));
			});
		}
		return lectures;
	}
	
	public LectureDto getLecture(int id) {
		Optional<Lecture> result = lectureRepository.findById(id);
		if (result.isPresent()) {
			return entityToDto(result.get());
		} else {
			return null;
		}
	}
	
	public List<LectureDto> getTeacherLecture(int teacherId) {
		List<LectureDto> result = new ArrayList<>(); 
	lectureRepository.findByTeacherIdContainsIgnoreCase(teacherId).forEach(lec->result.add(entityToDto(lec)));
		return result;
	}
}
