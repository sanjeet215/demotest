package com.example.demo.model.payload.service.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.payload.service.DepartmentService;
import com.example.demo.model.payload.service.EmplyoeeService;
import com.example.demo.model.payload.service.OrganizationService;

@Service
public class MyAnalyticsServices {

	private static final Logger logger = LoggerFactory.getLogger(MyAnalyticsServices.class);

	@Autowired
	OrganizationService orgService;

	@Autowired
	EmplyoeeService empService;

	@Autowired
	DepartmentService deptService;

	// Count number of Employee of the organization
	public Long countEmployeebyOrganizatoinId(Long orgid) {
		logger.debug("Counting employees of organization");
		return empService.countByOrganizationid(orgid);
	}

	// Count of Departments of the organization
	public Long countDepartmentsByOrganization(Long orgid) {
		logger.debug("Counting departments by organization");
		return deptService.countByOrgId(orgid);
	}

	// Count of organization --> active
	public Long getActiveOrganization() {
		return orgService.countActiveOrganization();
	}

	// Count of organization --> disabled
	public Long getDisabledOrganization() {
		return orgService.countDisabledOrganization();
	}
	
	//Count by Job type

	// Count of employees group by department
	// Employee count group by age
	// Average working hour of employees of organization
	// Sick leave trend monthly
	// monthly hiring

	// Super Admin

	// Count of organization
	// Count of Employees

}
