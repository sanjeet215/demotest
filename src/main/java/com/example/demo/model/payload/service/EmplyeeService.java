package com.example.demo.model.payload.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.model.payload.response.EmployeeResponse;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmplyeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmplyeeService.class);

	@Autowired
	EmployeeRepository empRepo;

	// Get Employee by id. Should fetch all employee related details
	public Employee getEmployeebyId(Long empId) {
		return empRepo.findByempId(empId).orElseThrow(() -> new ResourceNotFoundException("emp not found with empid"));
	}

	// Get Employee list for display as table. So specific details are required.
	public List<EmployeeResponse> getEmployeebyOrganization(String orgid) {

		logger.debug("extracting employee list for table display");
		
		List<EmployeeResponse> empList = new ArrayList<>();

		empRepo.findAll().forEach(item -> {

			EmployeeResponse response = new EmployeeResponse();
			response.setEmpEmailId(item.getEmpEmailId());
			response.setEmpFName(item.getEmpFName());
			response.setEmpLName(item.getEmpLName());
			response.setEmpMName(item.getEmpMName());
			response.setEmpPhoneNo(item.getEmpPhoneNo());
			response.setEmpRefId(item.getEmpRefId());

			empList.add(response);
		});

		return empList;

	}
}
