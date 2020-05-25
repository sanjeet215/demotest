package com.example.demo.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UnauthorizedAccess;
import com.example.demo.model.payload.service.EmplyoeeService;

@Service
public class UtlilityService {

	private static final Logger logger = LoggerFactory.getLogger(UtlilityService.class);

	@Autowired
	EmplyoeeService empservice;

	private String getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	public Long getOrgid(String email) {
		return empservice.getOrgIdbyEmail(email);
	}

	public Long authorize() {

		Long orgid;

		try {

			String currentUser = getCurrentUser();
			orgid = getOrgid(currentUser);
			//return orgid;
			return 10L;

		} catch (Exception ep) {
			logger.error("Error occured while validating user" + ep.getMessage());
			throw new UnauthorizedAccess("Unauthorized access prohibited.");
		}

	}
}
