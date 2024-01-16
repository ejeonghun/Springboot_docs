package com.example.cafe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "points")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PointEntity {
	
	@Id
	private String phone;
	
	private Long point;

}
