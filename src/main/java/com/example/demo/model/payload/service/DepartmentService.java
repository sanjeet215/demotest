package com.example.demo.model.payload.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.UnauthorizedAccess;
import com.example.demo.model.Department;
import com.example.demo.model.Organization;
import com.example.demo.model.payload.request.DepartmentRequest;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.OrganizationRepository;

@Service
public class DepartmentService {

	private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);

	@Autowired
	DepartmentRepository deptRepo;

	@Autowired
	OrganizationRepository orgRepo;

	/* Create Organization service */
	public Department createDepartment(DepartmentRequest deptRequest,Long orgid) {

		Optional<Organization> org = orgRepo.findByorgId(orgid);

		Department department = new Department();

		if (org.isPresent()) {
			department.setDeptName(deptRequest.getDeptName());
			department.setDescription(deptRequest.getDescription());
			department.setStatus(true);
			department.setOrganization(org.get());
		}

		return deptRepo.save(department);
	}

	/* Update Department */
	public Department updateDepartment(DepartmentRequest deptRequest) {

		Department department = deptRepo.findBydeptId(deptRequest.getDeptId())
				.orElseThrow(() -> new UnauthorizedAccess("Unauthorized access prohibited."));

		department.setDeptName(deptRequest.getDeptName());
		department.setDescription(deptRequest.getDescription());
		department.setStatus(deptRequest.isStatus());
		return deptRepo.save(department);

	}

	/* Disable department */
	public Department updateDepartment(Long deptId) {

		Department department = deptRepo.findBydeptId(deptId)
				.orElseThrow(() -> new UnauthorizedAccess("Unauthorized access prohibited."));
		department.setStatus(false);
		return deptRepo.save(department);
	}

	/* Get all departments for the organization id */
	public List<Department> getDepartments(Long orgid) {

		Organization org = orgRepo.findByorgId(orgid)
				.orElseThrow(() -> new UnauthorizedAccess("Unauthorized access prohibited."));

		Optional<List<Department>> deptList = deptRepo.findByOrganization(org);

		if (!deptList.isPresent()) {
			throw new ResourceNotFoundException("No departments present for organization");
		}

		return deptList.get();
	}

	/* Count of departments based on organization id */
	public Long countByOrgId(Long orgid) {

		Organization org = orgRepo.findByorgId(orgid)
				.orElseThrow(() -> new UnauthorizedAccess("Unauthorized access prohibited."));

		return deptRepo.countByOrganization(org);
	}

	/* Get Department by Id */
	public Department getDeptById(Long deptid) {
		return deptRepo.findBydeptId(deptid).orElseThrow(() -> new ResourceNotFoundException("no departments found"));
	}

	/* Get active departments for drop down on employee page */
	public List<Department> getActiveDepartments(Long orgid) {

		Organization org = orgRepo.findByorgId(orgid)
				.orElseThrow(() -> new UnauthorizedAccess("Unauthorized access prohibited."));

		Optional<List<Department>> deptList = deptRepo.findByOrganizationAndStatus(org, true);

		if (!deptList.isPresent()) {
			throw new ResourceNotFoundException("No departments present for organization");
		}

		return deptList.get();
	}
}
