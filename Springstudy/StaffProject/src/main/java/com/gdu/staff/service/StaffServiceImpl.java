package com.gdu.staff.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gdu.staff.domain.StaffDTO;
import com.gdu.staff.mapper.StaffMapper;

@Service
public class StaffServiceImpl implements StaffService {
	
	@Autowired
	private StaffMapper staffMapper;
	
	@Override
	public List<StaffDTO> getStaffList() {
		return staffMapper.selectStaffList();
	}
	
	@Override
	public ResponseEntity<String> addStaff(StaffDTO staff) {
		// staff에 salary 넣기(if or switch) : 기획부 1000, 개발부 2000, 영업부 3000, 나머지 4000
		try {
			
			if(staff.getDept().equals("기획부")) {
				staff.setSalary(5000);
			} else if(staff.getDept().equals("개발부")) {
				staff.setSalary(6000);
			} else if(staff.getDept().equals("영업부")) {
				staff.setSalary(7000);
			} else {
				staff.setSalary(4000);
			}
			
			staffMapper.insertStaff(staff);
			return new ResponseEntity<String>("사원 등록이 성공했습니다.", HttpStatus.OK); // 텍스트 그대로 응답시켜달라
			
		} catch (Exception e) {
			return new ResponseEntity<String>("사원 등록이 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public List<StaffDTO> findStaff(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}
