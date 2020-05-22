package com.example.demo.model.payload.response;

public class EmployeeResponse {

	private Long empId;
	private String empFName;
	private String empLName;
	private String empMName;
	private String empRefId;
	private String empPhoneNo;
	private String empEmailId;
	private String empImageUrl;
	private boolean status;

	public EmployeeResponse() {
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
		return "EmployeeResponse [empId=" + empId + ", empFName=" + empFName + ", empLName=" + empLName + ", empMName="
				+ empMName + ", empRefId=" + empRefId + ", empPhoneNo=" + empPhoneNo + ", empEmailId=" + empEmailId
				+ ", empImageUrl=" + empImageUrl + "]";
	}

}
