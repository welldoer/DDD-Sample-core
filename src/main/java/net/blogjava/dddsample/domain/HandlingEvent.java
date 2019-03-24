package net.blogjava.dddsample.domain;

import java.util.Date;

public class HandlingEvent implements Comparable<HandlingEvent>{
	private final Date time;
	private final Type type;
	private final CarrierMovement carrierMovement;

	public enum Type {
		LOAD, UNLOAD, RECEIVE, CLAIM
	}

	public HandlingEvent(Date time, Type type, CarrierMovement carrierMovement) {
		this.time = time;
		this.type = type;
		this.carrierMovement = carrierMovement;
	}

	public Type getType() {
		return type;
	}

	public CarrierMovement getCarrierMovement() {
		return carrierMovement;
	}
	
	public Date getTime() {
		return time;
	}

	/**
	 * Returns the Location of the Cargo. The location is calculated based on the following rules:
	 * <br>For
	 * <ul>
	 * <li> RECEIVE events: Location.NULL is returned. This basically means that the cargo is at its origin but not yet loaded on a CarrierMovment
	 * <li> CLAIM events: Location.NULL is returned. This means that the cargo is at its final destination and has been unloaded and claimed by the customer.
	 * <li> LOAD events: The from Location is returned.
	 * <li> UNLOAD events: The to Location is returned.
	 * </ul> 
	 * 
	 * @return The Location
	 */
	public Location getLocation() {
		Location location = Location.NULL;

		//My gosh! A switch statement....
		switch (type) {
		case LOAD:
			location = carrierMovement.from();
			break;
		case UNLOAD:
			location = carrierMovement.to();
			break;
		default: 
			// for others (RECEIVE, CLAIM) Location.NULL is fine...
			break;
		}
	
	    return location;
	}
	
	@Override
	public int compareTo(HandlingEvent o) {
		return time.compareTo(o.getTime());
	}

}
