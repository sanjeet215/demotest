package com.example.demo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.payload.request.DepartmentRequest;
import com.example.demo.model.payload.response.ApiResponse;
import com.example.demo.model.payload.service.DepartmentService;
import com.example.demo.utility.UtlilityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class DepartmentController {

	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	UtlilityService utilService;

	@Autowired
	DepartmentService deptService;

	@PostMapping("/department")
	public ResponseEntity<ApiResponse> createDepartment(@Valid @RequestBody DepartmentRequest deptRequest) {

		Long orgid = utilService.authorize();

		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(HttpStatus.CREATED.value(),
				"Department Created", deptService.createDepartment(deptRequest, orgid)));
	}

	/* Get departments by Organization id */
	@GetMapping("/department")
	public ResponseEntity<ApiResponse> getDepartmentsByOrganization() {

		Long orgid = utilService.authorize();

		return ResponseEntity.status(HttpStatus.OK).body(
				new ApiResponse(HttpStatus.OK.value(), "Departments extracted", deptService.getDepartments(orgid)));

	}

	@PutMapping("/department")
	public ResponseEntity<ApiResponse> updateDepartment(@Valid @RequestBody DepartmentRequest deptRequest) {
		
		utilService.authorize();

		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(HttpStatus.OK.value(), "Department updated",
				deptService.updateDepartment(deptRequest)));
	}

}
