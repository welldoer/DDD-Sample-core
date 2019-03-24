package net.blogjava.dddsample.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class HandlingEventTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCurrentLocationLoadEvent() {
		Location locationAAA = new Location("AAA");
		Location locationBBB = new Location("BBB");
		CarrierMovement cm = new CarrierMovement(locationAAA, locationBBB);
		
		HandlingEvent ev = new HandlingEvent(null, HandlingEvent.Type.LOAD, cm);
		
		assertThat(ev.getLocation()).isEqualTo(locationAAA);
	}

	@Test
	public void testCurrentLocationUnloadEvent() throws Exception {
		Location locationAAA = new Location("AAA");
		Location locationBBB = new Location("BBB");
		CarrierMovement cm = new CarrierMovement(locationAAA, locationBBB);

		HandlingEvent ev = new HandlingEvent(null, HandlingEvent.Type.UNLOAD, cm);

		assertThat(ev.getLocation()).isEqualTo(locationBBB);
	}

	@Test
	public void testCurrentLocationReceivedEvent() throws Exception {
		HandlingEvent ev = new HandlingEvent(null, HandlingEvent.Type.RECEIVE, null);

		assertThat(ev.getLocation()).isEqualTo(Location.NULL);
	}
	
	@Test
	public void testCurrentLocationClaimedEvent() throws Exception {
		HandlingEvent ev = new HandlingEvent(null, HandlingEvent.Type.CLAIM, null);

		assertThat(ev.getLocation()).isEqualTo(Location.NULL);
	}
}
