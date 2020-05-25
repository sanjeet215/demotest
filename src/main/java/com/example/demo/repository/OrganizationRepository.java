package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Long>{

	Optional<Organization> findByorgRefName(String orgRefName);
	
	Optional<Organization> findByorgId(Long orgId);
	
	boolean existsByorgRefName(String orgRefName);
	
	Long countByStatus(boolean status);
}
