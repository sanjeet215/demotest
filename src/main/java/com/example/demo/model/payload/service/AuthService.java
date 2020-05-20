package com.example.demo.model.payload.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.constants.MessageConstants;
import com.example.demo.exception.ResourceAlreadyExistException;
import com.example.demo.model.ERole;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.payload.request.OrganizationRequest;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utility.RandomPwdGenerator;

@Service
public class AuthService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	RandomPwdGenerator pwdGenerator;

	@Autowired
	PasswordEncoder encoder;

	/*
	 * This is the step to register user, data from organization creation request
	 * form. By default this user profile will be moderator
	 */
	@Transactional
	public User registerUser(OrganizationRequest empRequest) {

		if (Boolean.TRUE.equals(userRepository.existsByUsername(empRequest.getContactEmail()))) {
			throw new ResourceAlreadyExistException(MessageConstants.EMP_EMAILID_INUSE);
		}

		if (Boolean.TRUE.equals(userRepository.existsByEmail(empRequest.getContactEmail()))) {
			throw new ResourceAlreadyExistException(MessageConstants.EMP_EMAILID_INUSE);
		}

		/* Generate a random password and encode it */
		String password = encoder.encode(pwdGenerator.generateRandomPassword());

		User user = new User(empRequest.getContactEmail(), empRequest.getContactEmail(), password);

		Set<Role> roles = new HashSet<>();
		Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(modRole);

		user.setRoles(roles);
		return userRepository.save(user);

	}
}
