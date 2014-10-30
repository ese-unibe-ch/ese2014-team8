package org.sample.controller.pojos;

public class SearchForm {
	
	private long id;
	private int zipCode;
    private String appartment;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	
    public String getAppartment() {return appartment;}
    public void setAppartment(String appartment) {this.appartment = appartment;}
}
