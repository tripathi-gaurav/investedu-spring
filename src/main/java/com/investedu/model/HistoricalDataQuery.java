package com.investedu.model;

import java.util.List;

public class HistoricalDataQuery {

	private List<String> comapnies;
	private List<String> types;
	private String range;
	private String last;

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public void setComapnies(List<String> comapnies) {
		this.comapnies = comapnies;
	}

	public List<String> getComapnies() {
		return comapnies;
	}

	public void setCompanies(List<String> query) {
		this.comapnies = query;
	}
}
