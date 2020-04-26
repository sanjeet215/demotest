package com.example.demo.model.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class OrganizationRequest {

	@NotEmpty(message = "Organization unique name is required/Can't be blank")
	@Pattern(regexp = "\\s*\\S+\\s*", message = "Can't contain space in organization reference name")
	private String orgRefName;

	@NotEmpty(message = "Organization Name is required/Can't be blank")
	@Size(min = 2, max = 32, message = "orgName should be between 2 to 32 characters")
	private String orgName;

	@NotEmpty(message = "Description is required/Can't be blank")
	@Size(min = 2, max = 100, message = "description should be between 2 to 32 characters")
	private String description;

	@NotEmpty(message = "contact Name is required/Can't be blank")
	@Size(min = 2, max = 32, message = "contactName should be between 2 to 32 characters")
	private String contactName;

	@NotEmpty(message = "contact Number is required/Can't be blank")
	@Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message = "Mobile number is invalid")
	private String contactNumber;

	@Email
	private String contactEmail;

	public OrganizationRequest() {
		super();
	}

	public String getOrgRefName() {
		return orgRefName;
	}

	public void setOrgRefName(String orgRefName) {
		this.orgRefName = orgRefName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	@Override
	public String toString() {
		return "OrganizationRequest [orgRefName=" + orgRefName + ", orgName=" + orgName + ", description=" + description
				+ ", contactName=" + contactName + ", contactNumber=" + contactNumber + ", contactEmail=" + contactEmail
				+ "]";
	}

}
