package net.blogjava.dddsample.domain;

import java.util.Date;

public class HandlingEvent implements Comparable<HandlingEvent>{
	private final Date time;
	private final Type type;
	private final CarrierMovement carrierMovement;

	public enum Type {
		LOAD, UNLOAD;
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
	
	public Location getLocation() {
		return (type == HandlingEvent.Type.LOAD) ? carrierMovement.from() : carrierMovement.to();
	}
	
	@Override
	public int compareTo(HandlingEvent o) {
		return time.compareTo(o.getTime());
	}

}
