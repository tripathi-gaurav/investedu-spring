package com.investedu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Investment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_investment;
	private String username;
	private Double amount;
	private Integer duration;
	private String duration_type;
	public Integer getId_investment() {
		return id_investment;
	}
	public void setId_investment(Integer id_investment) {
		this.id_investment = id_investment;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public String getDuration_type() {
		return duration_type;
	}
	public void setDuration_type(String duration_type) {
		this.duration_type = duration_type;
	}
	
	
}
