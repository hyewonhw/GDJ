package com.gdu.staff.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.gdu.staff.domain.StaffDTO;

public interface StaffService {
	public List<StaffDTO> getStaffList();
	public ResponseEntity<String> addStaff(StaffDTO staff);  // 응답할 데이터는 String / staff 넘겨줌 
	public List<StaffDTO> findStaff(HttpServletRequest request);  // int sno 받아서 List<>로 반환
}
