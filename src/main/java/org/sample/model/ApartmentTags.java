package org.sample.model;

import javax.persistence.Entity;


@Entity
public class ApartmentTags extends RealEstateTags {
	
	private boolean kidFriendly;
	private boolean playgroundNearby;
	private boolean onBusyRoad;
	
	public boolean isKidFriendly() {
		return kidFriendly;
	}
	public void setKidFriendly(boolean kidFriendly) {
		this.kidFriendly = kidFriendly;
	}
	public boolean isPlaygroundNearby() {
		return playgroundNearby;
	}
	public void setPlaygroundNearby(boolean playgroundNearby) {
		this.playgroundNearby = playgroundNearby;
	}
	public boolean isOnBusyRoad() {
		return onBusyRoad;
	}
	public void setOnBusyRoad(boolean onBusyRoad) {
		this.onBusyRoad = onBusyRoad;
	}
	
}
