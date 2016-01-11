package com.hm.rest.entity;

public class CorporateCustomer extends Customer {
	private String corporateId;
	private String corporateAddress;

	public String getCorporateId() {
		return corporateId;
	}

	public void setCorporateId(String corporateId) {
		this.corporateId = corporateId;
	}

	public String getCorporateAddress() {
		return corporateAddress;
	}

	public void setCorporateAddress(String corporateAddress) {
		this.corporateAddress = corporateAddress;
	}

}
