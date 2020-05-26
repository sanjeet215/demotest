package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.example.demo.model.payload.service.app.MyAnalyticsServices;
import com.example.demo.utility.UtlilityService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AnalyticsController {

	private static final Logger logger = LoggerFactory.getLogger(AnalyticsController.class);

	@Autowired
	MyAnalyticsServices service;

	@Autowired
	UtlilityService utilService;

	@GetMapping("/empcount")
	public ResponseEntity<ApiResponse> countEmployee() {

		// service.countEmployeebyOrganizatoinId(utilService.authorize())

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse(HttpStatus.OK.value(), MessageConstants.EMP_EXTRACTED, Integer.toString(100)));
	}

	@GetMapping("/deptcount")
	public ResponseEntity<ApiResponse> countDepartment() {

		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(HttpStatus.OK.value(),
				// service.countDepartmentsByOrganization(utilService.authorize())
				MessageConstants.EMP_EXTRACTED, Integer.toString(500)));
	}

	@GetMapping("/gender")
	public ResponseEntity<ApiResponse> countByGender() {

		List<Integer> datapoints = new ArrayList<>();
		datapoints.add(50);
		datapoints.add(45);
		datapoints.add(5);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse(HttpStatus.OK.value(), MessageConstants.EMP_EXTRACTED, datapoints.toArray()));
	}

}
