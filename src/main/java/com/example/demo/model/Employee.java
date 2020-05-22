package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.configuration.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employee")
public class Employee extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = 3190498508871329951L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long empId;

	private String empFName;

	private String empLName;

	private String empMName;

	private String empRefId;

	private String empPhoneNo;

	private String empEmailId;

	private String empImageUrl;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "org_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Organization organization;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "emp_address_id")
	private Address address;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "salarystatic_id")
	private EmployeeSalaryDetails employeeSalaryDetails;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "job_id")
	private JobDetails jobDetails;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "DEPT_ID")
	private Department dept;

	private boolean status;

	public Employee() {
		super();
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpFName() {
		return empFName;
	}

	public void setEmpFName(String empFName) {
		this.empFName = empFName;
	}

	public String getEmpLName() {
		return empLName;
	}

	public void setEmpLName(String empLName) {
		this.empLName = empLName;
	}

	public String getEmpMName() {
		return empMName;
	}

	public void setEmpMName(String empMName) {
		this.empMName = empMName;
	}

	public String getEmpRefId() {
		return empRefId;
	}

	public void setEmpRefId(String empRefId) {
		this.empRefId = empRefId;
	}

	public String getEmpPhoneNo() {
		return empPhoneNo;
	}

	public void setEmpPhoneNo(String empPhoneNo) {
		this.empPhoneNo = empPhoneNo;
	}

	public String getEmpEmailId() {
		return empEmailId;
	}

	public void setEmpEmailId(String empEmailId) {
		this.empEmailId = empEmailId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public EmployeeSalaryDetails getEmployeeSalaryDetails() {
		return employeeSalaryDetails;
	}

	public void setEmployeeSalaryDetails(EmployeeSalaryDetails employeeSalaryDetails) {
		this.employeeSalaryDetails = employeeSalaryDetails;
	}

	public JobDetails getJobDetails() {
		return jobDetails;
	}

	public void setJobDetails(JobDetails jobDetails) {
		this.jobDetails = jobDetails;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public String getEmpImageUrl() {
		return empImageUrl;
	}

	public void setEmpImageUrl(String empImageUrl) {
		this.empImageUrl = empImageUrl;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empFName=" + empFName + ", empLName=" + empLName + ", empMName="
				+ empMName + ", empRefId=" + empRefId + ", empPhoneNo=" + empPhoneNo + ", empEmailId=" + empEmailId
				+ ", empImageUrl=" + empImageUrl + ", organization=" + organization + ", address=" + address
				+ ", employeeSalaryDetails=" + employeeSalaryDetails + ", jobDetails=" + jobDetails + ", dept=" + dept
				+ ", status=" + status + "]";
	}

}
