package com.example.cafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cafe.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

}

