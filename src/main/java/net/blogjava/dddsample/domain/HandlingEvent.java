package net.blogjava.dddsample.domain;

import java.util.Date;

public class HandlingEvent implements Comparable<HandlingEvent>{
	private final Date time;
	private final Type type;
	private final CarrierMovement carrierMovement;

	public enum Type {
		ON, OFF;
	}

	public HandlingEvent(Date time, Type type, CarrierMovement carrierMovement) {
		this.time = time;
		this.type = type;
		this.carrierMovement = carrierMovement;
	}

	public Type type() {
		return type;
	}

	public CarrierMovement getCarrierMovement() {
		return carrierMovement;
	}
	
	public Date time() {
		return time;
	}
	
	@Override
	public int compareTo(HandlingEvent o) {
		return time.compareTo(o.time());
	}

}
