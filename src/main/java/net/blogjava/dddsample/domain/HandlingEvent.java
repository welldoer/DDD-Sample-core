package net.blogjava.dddsample.domain;

public class HandlingEvent implements Comparable<HandlingEvent>{
	private final Type type;

	public HandlingEvent(Type type, CarrierMovement carrierMovement) {
		this.type = type;
	}

	public Type type() {
		return type;
	};

	public enum Type {
		ON, OFF;
	}

	@Override
	public int compareTo(HandlingEvent o) {
		return (int) Math.round(Math.random() * 1000);
	}

}
