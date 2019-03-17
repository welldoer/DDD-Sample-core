package net.blogjava.dddsample.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.SortedSet;

import org.junit.Before;
import org.junit.Test;

public class TrackingScenarioTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTrackingScenarioStage1() {
		Cargo cargo = populateCargo();
		
		DeliveryHistory deliveryHistory = cargo.deliveryHistory();
		
		SortedSet<HandlingEvent> handlingEvents = deliveryHistory.events();
		
		assertThat(handlingEvents.size()).isEqualTo(4);
		final HandlingEvent event = handlingEvents.last();
		assertThat(event.type()).isEqualTo(HandlingEvent.Type.OFF);
		assertThat(cargo.atFinalDestiation()).isFalse();
		assertThat(cargo.currentLocation().unlocode()).isEqualTo("CNHKG");
	}

	private Cargo populateCargo() {
		final Cargo cargo = new Cargo(new TrackingId("XYZ"), new Location("SEST0"), new Location("AUMEL"));
		
		final CarrierMovement stockholmToHamburg = new CarrierMovement(new Location("SESTO"), new Location("DEHAM"));
		
		cargo.handle(new HandlingEvent(HandlingEvent.Type.ON, stockholmToHamburg));
		cargo.handle(new HandlingEvent(HandlingEvent.Type.OFF, stockholmToHamburg));
		
		final CarrierMovement hamburgToHongKong = new CarrierMovement(new Location("DEHAM"), new Location("CNHGK"));
		
		cargo.handle(new HandlingEvent(HandlingEvent.Type.ON, hamburgToHongKong));
		cargo.handle(new HandlingEvent(HandlingEvent.Type.OFF, hamburgToHongKong));
		
		return cargo;
	}

}
