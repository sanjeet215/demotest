package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.MessageConstants;
import com.example.demo.model.payload.response.ApiResponse;
import com.example.demo.model.payload.service.EmplyoeeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmplyoeeService empService;

	@GetMapping("/emp")
	public ResponseEntity<ApiResponse> getAllEmployees() {

		logger.info("Get all Employees controller and extracting all employees basic information");
		
		return ResponseEntity.status(HttpStatus.OK).body(
				new ApiResponse(HttpStatus.OK.value(), MessageConstants.EMP_EXTRACTED, empService.getAllEmployee()));
	}

}
