package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;
import com.example.demo.model.Organization;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByempId(Long empid);

	Optional<Employee> findByEmpEmailId(String emailId);

	boolean existsByEmpEmailId(String emailId);

	boolean existsByEmpPhoneNo(String phoneno);

	long countByOrganization(Organization org);
}
