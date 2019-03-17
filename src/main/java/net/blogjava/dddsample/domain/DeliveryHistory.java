package net.blogjava.dddsample.domain;

import java.util.SortedSet;
import java.util.TreeSet;

public class DeliveryHistory {
	private TreeSet<HandlingEvent> events;

	public DeliveryHistory() {
		this.events = new TreeSet<>();
	}

	public SortedSet<HandlingEvent> events() {
		return events;
	}

	public HandlingEvent last() {
		return events.last();
	}

	public void add(HandlingEvent event) {
		events.add(event);
	}

}
