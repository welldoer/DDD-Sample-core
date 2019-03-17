package net.blogjava.dddsample.domain;

import java.util.function.IntPredicate;

public class Location {
	private final String unlocode;

	public Location(String unlocode) {
		this.unlocode = unlocode;
	}

	public String unlocode() {
		return unlocode;
	}

}
