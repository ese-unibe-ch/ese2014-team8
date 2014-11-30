package org.sample.model;

import javax.persistence.Entity;


@Entity
public class ShApartmentTags extends RealEstateTags {
	
	private boolean eatingCookingTogether;
	private boolean stayingWeekends;
	private boolean vegetarianVegan;
	private boolean nonVegetarian;
	
	public boolean isEatingCookingTogether() {
		return eatingCookingTogether;
	}
	public void setEatingCookingTogether(boolean eatingCookingTogether) {
		this.eatingCookingTogether = eatingCookingTogether;
	}
	public boolean isStayingWeekends() {
		return stayingWeekends;
	}
	public void setStayingWeekends(boolean stayingWeekends) {
		this.stayingWeekends = stayingWeekends;
	}
	public boolean isVegetarianVegan() {
		return vegetarianVegan;
	}
	public void setVegetarianVegan(boolean vegetarianVegan) {
		this.vegetarianVegan = vegetarianVegan;
	}
	public boolean isNonVegetarian() {
		return nonVegetarian;
	}
	public void setNonVegetarian(boolean nonVegetarian) {
		this.nonVegetarian = nonVegetarian;
	}
	
}
