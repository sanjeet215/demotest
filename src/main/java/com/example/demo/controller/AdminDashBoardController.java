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
import com.example.demo.model.payload.service.OrganizationService;
import com.example.demo.model.payload.service.app.SuperAdminServices;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AdminDashBoardController {

	@Autowired
	SuperAdminServices service;
	
	@Autowired
	OrganizationService orgService;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminDashBoardController.class);
	
	// All organization count including active and disabled.
		@GetMapping("/org/count")
		public ResponseEntity<ApiResponse> organizationCount() {
			logger.debug("Counting organization including both active and inactive");

			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(HttpStatus.OK.value(),
					MessageConstants.COUNT_EXTRACTED, orgService.countOrganization()));
		}

		// Get organization by count
		@GetMapping("/org/countstatus")
		public ResponseEntity<ApiResponse> organizationCountByStatus() {
			logger.debug("Counting organization group by active and inactive");

			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(HttpStatus.OK.value(),
					MessageConstants.COUNT_EXTRACTED, orgService.countOrganizationgroupbyStatus()));
		}
}
