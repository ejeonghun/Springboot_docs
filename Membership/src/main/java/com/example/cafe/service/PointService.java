package com.example.cafe.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.cafe.dto.PointDto;
import com.example.cafe.entity.PointEntity;
import com.example.cafe.repository.PointRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PointService {
	private final PointRepository pointRepository;
	
	private PointDto EntitytoDto(PointEntity entity) {
		return new PointDto(entity.getPhone(),
							entity.getPoint());
	}
	
	
	
	public PointDto SearchPhone(String phone) {
		Optional<PointEntity> res = pointRepository.findById(phone);
		if (res.isPresent()) {
			PointEntity entity = res.get();
			return EntitytoDto(entity); // 검색
		} else {
			return CreateUser(phone); // 없으면 생성
		}
	}

	public PointDto CreateUser(String phone) {
		Optional<PointEntity> res = pointRepository.findById(phone);
		if (res.isEmpty()) {
			Long point = (long) 0;
			PointEntity input = new PointEntity(phone, point);
			pointRepository.save(input);
			return EntitytoDto(input);
		} else { return null; }
	}
	
	
	public PointDto ChangePoint(PointDto dto) {
		PointEntity res = pointRepository.save(new PointEntity(dto.getPhone(), dto.getPoint()));
		return EntitytoDto(res);
	}
//		Optional<PointEntity> check = pointRepository.findById(dto.getPhone());
//		if (check.isPresent()) {
//			PointEntity user = check.get();
//			user.setPoint(dto.getPoint());
//			PointEntity res = pointRepository.save(user);
//			return EntitytoDto(res);
//		}
//		else { return null; } // 존재하지 않는 Phone 번호일 때
//	}
	
}
