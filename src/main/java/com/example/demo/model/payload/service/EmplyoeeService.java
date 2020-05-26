package com.example.demo.model.payload.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.constants.MessageConstants;
import com.example.demo.exception.ResourceAlreadyExistException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.SomeInternalExceptionOccured;
import com.example.demo.exception.UnauthorizedAccess;
import com.example.demo.model.Employee;
import com.example.demo.model.Organization;
import com.example.demo.model.payload.request.OrganizationRequest;
import com.example.demo.model.payload.response.EmployeeResponse;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.OrganizationRepository;

@Service
// @CacheConfig(cacheNames = "employees")
public class EmplyoeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmplyoeeService.class);

	@Autowired
	EmployeeRepository empRepo;

	@Autowired
	OrganizationRepository orgRepo;
	
	@Autowired
	OrganizationService orgService;

	@Transactional
	public Employee createEmployee(OrganizationRequest empRequest, Organization org) {

		/* step 1 - Check if employee exist with email id */
		if (empRepo.existsByEmpEmailId(empRequest.getContactEmail())) {
			logger.debug("Email id is already taken!.");
			throw new ResourceAlreadyExistException(MessageConstants.EMP_EMAILID_INUSE);
		}

		/* 2 - Create employee */
		Employee employee = new Employee();
		try {
			employee.setEmpFName(empRequest.getFirstName());
			employee.setEmpLName(empRequest.getLastName());
			employee.setEmpEmailId(empRequest.getContactEmail());
			employee.setEmpPhoneNo(empRequest.getContactNumber());
			employee.setOrganization(org);
			employee.setStatus(true);
		} catch (Exception ep) {
			logger.error("Error occured while registering an employeea");
			throw new SomeInternalExceptionOccured(ep.getLocalizedMessage());
		}

		return empRepo.save(employee);

	}

	// Get Employee by id. Should fetch all employee related details
	public Employee getEmployeebyId(Long empId) {
		return empRepo.findByempId(empId).orElseThrow(() -> new ResourceNotFoundException("emp not found with empid"));
	}

	// Get Employee list for display as table. So specific details are required.
	public List<EmployeeResponse> getAllEmployee() {

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
			response.setEmpImageUrl(item.getEmpImageUrl());
			response.setEmpId(item.getEmpId());
			response.setStatus(item.isStatus());

			empList.add(response);
		});

		return empList;

	}

	@Cacheable(value = "employeesCache", key = "#email")
	public Long getOrgIdbyEmail(String email) {

		Long orgId = 10L;

		Optional<Employee> emp = empRepo.findByEmpEmailId(email);
		if (!emp.isPresent()) {
			throw new UnauthorizedAccess("Unauthorized access prohibited.");
		} else {
			orgId = emp.get().getOrganization().getOrgId();
			logger.info("Cache testing");
		}

		return orgId;
	}


	public Long countByOrganizationid(Long orgid) {
		return empRepo.countByOrganization(orgService.getOrganizatiobyId(orgid));
	}

}
