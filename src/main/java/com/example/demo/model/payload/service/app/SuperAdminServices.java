package com.example.demo.model.payload.service.app;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Organization;
import com.example.demo.model.payload.request.OrganizationRequest;
import com.example.demo.model.payload.service.AuthService;
import com.example.demo.model.payload.service.EmplyoeeService;
import com.example.demo.model.payload.service.OrganizationService;

@Service
public class SuperAdminServices {

	private static final Logger logger = LoggerFactory.getLogger(SuperAdminServices.class);
	
	@Autowired
	AuthService authService;
	
	@Autowired
	OrganizationService orgService;
	
	@Autowired
	EmplyoeeService empService;
	
	@Transactional
	public void onBoardOrganization(OrganizationRequest orgRequest) {
		
		/*1. Make an entry in organiation table*/
		Organization org = orgService.createOrganization(orgRequest);
		
		/*2. Make an entry in Employee table*/
		empService.createEmployee(orgRequest,org);
		
		/*3. Register user to allow login to application*/
		authService.registerUser(orgRequest);		
		
	}
	
}
