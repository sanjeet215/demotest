package com.example.demo.configuration;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}

		String currentPrincipalName = authentication.getName();
		
		if(!(currentPrincipalName.toString() != null)) {
			return  Optional.of("Admin");
		}

		return Optional.of(currentPrincipalName.toString());
	}

}
