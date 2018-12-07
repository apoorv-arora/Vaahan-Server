package com.rivigo.database.classes;

import java.io.Serializable;

public class User implements Serializable {

	private int userId;
	private String name;
	private String number;
	private long created;
	private long imageUrl;
	private boolean ownsTruck;

	private String panUrl;
	private String rcUrl;
	private String dlUrl;
	private String insuranceUrl;
	
	public User() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public long getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(long imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isOwnsTruck() {
		return ownsTruck;
	}

	public void setOwnsTruck(boolean ownsTruck) {
		this.ownsTruck = ownsTruck;
	}

	public String getPanUrl() {
		return panUrl;
	}

	public void setPanUrl(String panUrl) {
		this.panUrl = panUrl;
	}

	public String getRcUrl() {
		return rcUrl;
	}

	public void setRcUrl(String rcUrl) {
		this.rcUrl = rcUrl;
	}

	public String getDlUrl() {
		return dlUrl;
	}

	public void setDlUrl(String dlUrl) {
		this.dlUrl = dlUrl;
	}

	public String getInsuranceUrl() {
		return insuranceUrl;
	}

	public void setInsuranceUrl(String insuranceUrl) {
		this.insuranceUrl = insuranceUrl;
	}
	
}
