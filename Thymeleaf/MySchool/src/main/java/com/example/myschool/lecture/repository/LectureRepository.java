package com.example.myschool.lecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.myschool.lecture.domain.Lecture;
import com.example.myschool.lecture.dto.LectureDto;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {
	public List<Lecture> findByTitleContainsIgnoreCase(String title);
	
    @Query(value = "SELECT l FROM Lecture l WHERE l.teacher.teacherId = :teacherId")
    public List<Lecture> findByTeacherIdContainsIgnoreCase(@Param("teacherId") int teacherId);

}