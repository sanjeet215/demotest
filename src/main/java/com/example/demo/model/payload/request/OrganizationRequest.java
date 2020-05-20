package com.example.demo.model.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/* Organization on-boarding Request */

public class OrganizationRequest {

	@NotEmpty(message = "Organization unique name is required/Can't be blank")
	@Pattern(regexp = "\\s*\\S+\\s*", message = "Can't contain space in organization reference name")
	@Size(min = 3, max = 10, message = "orgName should be between 3 to 10 characters")
	private String orgRefName;

	@NotEmpty(message = "Organization Name is required/Can't be blank")
	@Size(min = 3, max = 50, message = "orgName should be between 3 to 50 characters")
	private String orgName;

	@NotEmpty(message = "Description is required/Can't be blank")
	@Size(min = 1, max = 100, message = "description should be between 1 to 100 characters")
	private String description;

	@NotEmpty(message = "First Name is required/Can't be blank")
	@Size(min = 1, max = 16, message = "First Name should be between 1 to 16 characters")
	private String firstName;

	@NotEmpty(message = "Last Name is required/Can't be blank")
	@Size(min = 1, max = 16, message = "Last Name should be between 1 to 16 characters")
	private String lastName;

	@NotEmpty(message = "contact Number is required/Can't be blank")
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", contactNumber=" + contactNumber
				+ ", contactEmail=" + contactEmail + "]";
	}

}
