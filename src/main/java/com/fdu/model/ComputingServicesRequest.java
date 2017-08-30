package com.fdu.model;

/**
 * A general request class that can be used with any URI.<br/>
 * Only certain fields can be non-values - this depends on the URI.
 * 
 * @author arifakrammohammed
 *
 */
public class ComputingServicesRequest {

	String Id;
	String startDate;
	String endDate;
	String givenDate;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getGivenDate() {
		return givenDate;
	}

	public void setGivenDate(String givenDate) {
		this.givenDate = givenDate;
	}

}
