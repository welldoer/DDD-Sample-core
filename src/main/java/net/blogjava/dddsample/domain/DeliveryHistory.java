package net.blogjava.dddsample.domain;

import java.util.SortedSet;
import java.util.TreeSet;

public class DeliveryHistory {

	public SortedSet<HandlingEvent> events() {
		SortedSet<HandlingEvent> temp = new TreeSet<>();
		temp.add(new HandlingEvent(HandlingEvent.Type.ON, new CarrierMovement(new Location("1"), new Location("2"))));
		temp.add(new HandlingEvent(HandlingEvent.Type.OFF, new CarrierMovement(new Location("2"), new Location("2"))));
		temp.add(new HandlingEvent(HandlingEvent.Type.ON, new CarrierMovement(new Location("3"), new Location("2"))));
		temp.add(new HandlingEvent(HandlingEvent.Type.OFF, new CarrierMovement(new Location("3"), new Location("2"))));
		return temp;
	}

}
