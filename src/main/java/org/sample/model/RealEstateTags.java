package org.sample.model;

import javax.persistence.*;

@MappedSuperclass
public class RealEstateTags {

	@Id
	@GeneratedValue
	private Long id;
	
	private boolean smokingAllowed;
	private boolean petsAllowed;
	private boolean musicInstrumentsAllowed;
	private boolean bikeParking;
	private boolean carParking;
	private boolean sharedGarden;
	private boolean balcony;
	private boolean quietNeighbourhood;
	private boolean elevator;
	private boolean wheelchairAccessible;
	private boolean lowEnergyBuilding;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isSmokingAllowed() {
		return smokingAllowed;
	}
	public void setSmokingAllowed(boolean smokingAllowed) {
		this.smokingAllowed = smokingAllowed;
	}
	public boolean isPetsAllowed() {
		return petsAllowed;
	}
	public void setPetsAllowed(boolean petsAllowed) {
		this.petsAllowed = petsAllowed;
	}
	public boolean isMusicInstrumentsAllowed() {
		return musicInstrumentsAllowed;
	}
	public void setMusicInstrumentsAllowed(boolean musicInstrumentsAllowed) {
		this.musicInstrumentsAllowed = musicInstrumentsAllowed;
	}
	public boolean isBikeParking() {
		return bikeParking;
	}
	public void setBikeParking(boolean bikeParking) {
		this.bikeParking = bikeParking;
	}
	public boolean isCarParking() {
		return carParking;
	}
	public void setCarParking(boolean carParking) {
		this.carParking = carParking;
	}
	public boolean isSharedGarden() {
		return sharedGarden;
	}
	public void setSharedGarden(boolean sharedGarden) {
		this.sharedGarden = sharedGarden;
	}
	public boolean isBalcony() {
		return balcony;
	}
	public void setBalcony(boolean balcony) {
		this.balcony = balcony;
	}
	public boolean isQuietNeighbourhood() {
		return quietNeighbourhood;
	}
	public void setQuietNeighbourhood(boolean quietNeighbourhood) {
		this.quietNeighbourhood = quietNeighbourhood;
	}
	public boolean isElevator() {
		return elevator;
	}
	public void setElevator(boolean elevator) {
		this.elevator = elevator;
	}
	public boolean isWheelchairAccessible() {
		return wheelchairAccessible;
	}
	public void setWheelchairAccessible(boolean wheelchairAccessible) {
		this.wheelchairAccessible = wheelchairAccessible;
	}
	public boolean isLowEnergyBuilding() {
		return lowEnergyBuilding;
	}
	public void setLowEnergyBuilding(boolean lowEnergyBuilding) {
		this.lowEnergyBuilding = lowEnergyBuilding;
	}
	
}
