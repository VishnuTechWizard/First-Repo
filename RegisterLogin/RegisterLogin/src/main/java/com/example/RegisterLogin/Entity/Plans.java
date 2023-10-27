package com.example.RegisterLogin.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Plans {

	@Id
	private String id;
	private String username;
	private String plan;
	private long price;
	private String priceType;
	

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Plans(String id, String username, String plan, long price, String priceType) {
		super();
		this.id = id;
		this.username = username;
		this.plan = plan;
		this.price = price;
		this.priceType = priceType;
	}

	public Plans() {
		super();
		// TODO Auto-generated constructor stub
	}



}
