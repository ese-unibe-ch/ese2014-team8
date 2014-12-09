package org.sample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Address {
	  @Id
	    @GeneratedValue
	    private Long id;

	    private String street;
	    private int number;
		private String city;
		private Integer zipCode;


		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public Integer getZipCode() {
			return zipCode;
		}

		public void setZipCode(Integer zipCode) {
			this.zipCode = zipCode;
		}
	    
	    
	  
}
