package com.investedu.model;


public class Company {

	private Integer id_company;
	private String company_name;
	private String symbol;
	private String sector;
	private String industry;
	public Integer getId_company() {
		return id_company;
	}
	public void setId_company(Integer id_company) {
		this.id_company = id_company;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
}
