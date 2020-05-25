package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Department;
import com.example.demo.model.Organization;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Optional<List<Department>> findByOrganization(Organization org);

	Long countByOrganization(Organization org);

	Optional<Department> findBydeptId(Long deptId);

	Optional<List<Department>> findByOrganizationAndStatus(Organization org, boolean b);
}
