package org.sample.controller.pojos;

import java.util.List;
/**
 * The searchForm at the moment only contains very basic search criteria.
 * 
 *
 */
public class SearchForm{
	
	private long id;
	private Integer zipCode;
	private String city;
	private Integer minPrice;
	private Integer maxPrice;
    private String category;
    
	private List<String> categories;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}

	public Integer getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}
}
