package com.example.demo.model.payload.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class DepartmentRequest {

	private Long deptId;

	@NotEmpty(message = "Department name is required/Can't be blank")
	@Size(min = 1, max = 50, message = "orgName should be between 1 to 50 characters")
	private String deptName;

	@NotEmpty(message = "Department description is required/Can't be blank")
	@Size(min = 1, max = 50, message = "description should be between 1 to 50 characters")
	private String description;

	private boolean status = true;

	public DepartmentRequest() {
		super();
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

}
