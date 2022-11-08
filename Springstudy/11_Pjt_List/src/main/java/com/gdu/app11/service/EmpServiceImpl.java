package com.gdu.app11.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.app11.domain.EmpDTO;
import com.gdu.app11.mapper.EmpMapper;


@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmpMapper empMapper;
	
	@Override
	public void findAllEmployees(HttpServletRequest request, Model model) {
		
		// request에서 page 파라미터 꺼내기
		// page 파라미터가 전달되지 않는 경우 page = 1로 처리한다.
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		/*
			rownum은 줄세운후 붙이는 번호이기때문에 rownum을 기준으로 가져오기
		    page    begin   end   totalRecord(34)
		 	1		1		10	    10 
		 	2		11		20	 	20
		 	3		21		30		30
		 	4       31		40      34
		*/ 
		
		int totalRecord = empMapper.selectAllEmployeesCount();
		
		int recordPerPage = 10; // 10개씩
		int begin = (page - 1) * recordPerPage + 1;
		int end = begin + recordPerPage - 1;
		if(end > totalRecord) {
			end = totalRecord;
		}
		
		List<EmpDTO> employees = empMapper.selectEmployeesByPage(begin, end);
		
		model.addAttribute("employees", employees);
		
	}

}
