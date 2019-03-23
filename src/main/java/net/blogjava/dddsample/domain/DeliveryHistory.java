package net.blogjava.dddsample.domain;

import java.util.SortedSet;
import java.util.TreeSet;

public class DeliveryHistory {
	private final SortedSet<HandlingEvent> events = new TreeSet<>();

	public SortedSet<HandlingEvent> events() {
		return events;
	}

	public void addEvent(HandlingEvent event) {
		events.add(event);
	}

	public HandlingEvent last() {
		return events.isEmpty() ? null : events.last();
	}

}
