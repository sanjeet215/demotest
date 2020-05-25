package com.example.demo.model.payload.response;

public class OrganizationCountResponse {
	private String itemName;
	private Long count;

	public OrganizationCountResponse() {
		super();
	}

	public OrganizationCountResponse(String itemName, Long count) {
		super();
		this.itemName = itemName;
		this.count = count;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "OrganizationCountResponse [itemName=" + itemName + ", count=" + count + "]";
	}

}
