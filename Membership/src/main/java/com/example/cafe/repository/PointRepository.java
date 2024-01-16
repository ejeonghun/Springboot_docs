package com.example.cafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cafe.entity.PointEntity;

public interface PointRepository extends JpaRepository<PointEntity, String> {


}
