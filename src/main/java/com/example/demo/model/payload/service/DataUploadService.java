package com.example.demo.model.payload.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.constants.MessageConstants;
import com.example.demo.exception.ResourceAlreadyExistException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CustomErrors;
import com.example.demo.model.Department;
import com.example.demo.model.ERole;
import com.example.demo.model.Employee;
import com.example.demo.model.Organization;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.payload.request.DepartmentRequest;
import com.example.demo.model.payload.request.OrganizationRequest;
import com.example.demo.repository.CustomErrorRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.OrganizationRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utility.RandomPwdGenerator;

@Service
public class DataUploadService {

	private static final Logger logger = LoggerFactory.getLogger(DataUploadService.class);

	@Autowired
	OrganizationRepository orgRepo;

	@Autowired
	EmployeeRepository empRepo;

	@Autowired
	AuthService authService;

	@Autowired
	CustomErrorRepository errorRepo;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	RandomPwdGenerator pwdGenerator;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	DepartmentRepository deptRepo;

	/* Method to upload departments */
	public boolean uploadDepartment(DepartmentRequest deptRequest, Long orgid) {

		boolean status = false;

		Optional<Organization> org = orgRepo.findByorgId(orgid);

		Department department = new Department();

		if (org.isPresent()) {
			throw new ResourceNotFoundException("Org Id not found");
		}

		try {

			if (deptRepo.findByDeptNameAndOrganization(deptRequest.getDeptName(), org.get()).isPresent()) {
				throw new ResourceAlreadyExistException("Department with department already exists");
			}

			department.setDeptName(deptRequest.getDeptName());
			department.setDescription(deptRequest.getDescription());
			department.setStatus(true);
			department.setOrganization(org.get());

			deptRepo.save(department);
			status = true;
		} catch (ResourceNotFoundException cex) {
			errorRepo.save(
					new CustomErrors("some internal error occured", "unauthorized access", cex.getLocalizedMessage()));
			status = false;
		} catch (ResourceAlreadyExistException ceex) {
			errorRepo.save(new CustomErrors("Department Name already exist", deptRequest.getDeptName(),
					ceex.getLocalizedMessage()));
			status = false;
		} catch (Exception ep) {
			errorRepo.save(
					new CustomErrors("exception in saving data", deptRequest.getDeptName(), ep.getLocalizedMessage()));
			status = false;
		}

		return status;
	}

	/* Method is to upload organization Excel sheet */
	public boolean uploadOrganization(OrganizationRequest orgRequest) {

		boolean status = false;

		Organization organization = new Organization();

		try {

			if (orgRepo.existsByorgRefName(orgRequest.getOrgRefName())) {
				logger.debug("Organization Reference Name is already taken.");
				throw new ResourceAlreadyExistException(MessageConstants.ORG_ID_IN_USE);
			}

			// Check email id
			if (empRepo.existsByEmpEmailId(orgRequest.getContactEmail())) {
				logger.debug("Email id is already taken!.");
				throw new ResourceAlreadyExistException(MessageConstants.EMP_EMAILID_INUSE);
			}

			// Check mobile number
			if (empRepo.existsByEmpEmailId(orgRequest.getContactNumber())) {
				logger.debug("Contact number is already taken!.");
				throw new ResourceAlreadyExistException(MessageConstants.CONTACT_ALREADY_IN_USE);
			}

			// Check if email id is there in user repository
			if (Boolean.TRUE.equals(userRepository.existsByUsername(orgRequest.getContactEmail()))) {
				logger.debug("email id already present in user Repository");
				throw new ResourceAlreadyExistException(MessageConstants.EMP_EMAILID_INUSE);
			}

			// Check if user id is there in user repository
			if (Boolean.TRUE.equals(userRepository.existsByEmail(orgRequest.getContactEmail()))) {
				logger.debug("user id already present in user Repository");
				throw new ResourceAlreadyExistException(MessageConstants.EMP_EMAILID_INUSE);
			}

			// insert into Organization

			organization.setOrgName(orgRequest.getOrgName());
			organization.setOrgRefName(orgRequest.getOrgRefName());
			organization.setDescription(orgRequest.getDescription());
			organization.setStatus(true);
			Organization newOrg = orgRepo.save(organization);

			// insert into employee

			Employee employee = new Employee();

			employee.setEmpFName(orgRequest.getFirstName());
			employee.setEmpLName(orgRequest.getLastName());
			employee.setEmpEmailId(orgRequest.getContactEmail());
			employee.setEmpPhoneNo(orgRequest.getContactNumber());
			employee.setOrganization(newOrg);
			employee.setStatus(true);
			empRepo.save(employee);

			// insert into user table.User login creation

			String password = encoder.encode(pwdGenerator.generateRandomPassword());

			User user = new User(orgRequest.getContactEmail(), orgRequest.getContactEmail(), password);

			Set<Role> roles = new HashSet<>();
			Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(modRole);

			user.setRoles(roles);
			userRepository.save(user);

			status = true;

		} catch (ResourceAlreadyExistException cexp) {
			status = false;
			errorRepo.save(new CustomErrors(orgRequest.toString() + " is already taken!", orgRequest.toString(),
					cexp.getLocalizedMessage()));
		} catch (Exception ep) {
			status = false;
			logger.error("Error occured while  assigning the object");
			errorRepo.save(new CustomErrors("Issue in data type", orgRequest.toString(), ep.getLocalizedMessage()));

		}

		return status;
	}
}
