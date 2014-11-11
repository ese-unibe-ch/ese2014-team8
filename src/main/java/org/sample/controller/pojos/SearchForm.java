package org.sample.controller.pojos;

import java.util.List;
/**
 * The searchForm at the moment only contains very basic search criteria.
 * 
 *
 */
public class SearchForm{
	
	private long id;
	private int zipCode;
    private String category;
    
	private List<String> categories;

	
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

	
    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
  
}
