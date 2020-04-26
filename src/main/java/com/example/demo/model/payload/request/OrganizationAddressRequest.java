package com.example.demo.model.payload.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class OrganizationAddressRequest {

	@NotNull(message = "Please enter id and can't be null")
	private Long orgId;

	@NotEmpty(message = "Address Line 1 is required/Can't be blank")
	private String addressLine1;

	private String addressLine2;

	private String street;

	private String city;

	private String state;

	private String country;

	private String zipCode;

	public OrganizationAddressRequest() {
		super();
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "OrganizationAddressRequest [addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
				+ ", street=" + street + ", city=" + city + ", state=" + state + ", country=" + country + ", zipCode="
				+ zipCode + "]";
	}

}
