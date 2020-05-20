package com.example.demo.model.payload.response;

public class OrganizationResponse {

	private Long orgId;
	private String orgRefName;
	private String orgName;
	private String description;
	private boolean status;
	private String imaeUrl;

	public OrganizationResponse() {
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getImaeUrl() {
		return imaeUrl;
	}

	public void setImaeUrl(String imaeUrl) {
		this.imaeUrl = imaeUrl;
	}

	@Override
	public String toString() {
		return "OrganizationResponse [orgId=" + orgId + ", orgRefName=" + orgRefName + ", orgName=" + orgName
				+ ", description=" + description + ", status=" + status + ", imaeUrl=" + imaeUrl + "]";
	}

}
