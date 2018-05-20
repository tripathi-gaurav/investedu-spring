package com.investedu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PreferredIndustry {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id_favorite_industry_of_user;
	private String username;
	private String sector;

	
	public int getId_favorite_industry_of_user() {
		return id_favorite_industry_of_user;
	}
	public void setId_favorite_industry_of_user(int id_favorite_industry_of_user) {
		this.id_favorite_industry_of_user = id_favorite_industry_of_user;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
}
