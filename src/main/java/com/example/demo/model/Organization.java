package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.example.demo.configuration.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "organization")
public class Organization extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = 2302599750944454135L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orgId;

	private String orgRefName;

	private String orgName;

	private String description;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "org_address_id")
	private Address address;
	
	private boolean status;

	private String imageUrl;

	public Organization() {
		super();
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Organization [orgId=" + orgId + ", orgRefName=" + orgRefName + ", orgName=" + orgName + ", description="
				+ description + ", address=" + address + ", status=" + status + ", imageUrl=" + imageUrl + "]";
	}

}
