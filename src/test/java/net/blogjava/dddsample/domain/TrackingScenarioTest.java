package net.blogjava.dddsample.domain;

import static org.assertj.core.api.Assertions.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedSet;

import org.junit.Before;
import org.junit.Test;

public class TrackingScenarioTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTrackingScenarioStage1() throws Exception {
		Cargo cargo = populateCargo();
		
		DeliveryHistory deliveryHistory = cargo.getDeliveryHistory();
		
		SortedSet<HandlingEvent> handlingEvents = deliveryHistory.events();
		
		assertThat(handlingEvents.size()).isEqualTo(4);
		final HandlingEvent event = handlingEvents.last();
		assertThat(event.getType()).isEqualTo(HandlingEvent.Type.UNLOAD);
		assertThat(cargo.atFinalDestiation()).isFalse();
		assertThat(cargo.getCurrentLocation().unlocode()).isEqualTo("CNHKG");
	}

	private Cargo populateCargo() throws Exception {
		final Cargo cargo = new Cargo(new TrackingId("XYZ"), new Location("SEST0"), new Location("AUMEL"));
		
		final CarrierMovement stockholmToHamburg = new CarrierMovement(new Location("SESTO"), new Location("DEHAM"));
		
		cargo.handle(new HandlingEvent(getDate("2007-12-01"), HandlingEvent.Type.LOAD, stockholmToHamburg));
		cargo.handle(new HandlingEvent(getDate("2007-12-02"), HandlingEvent.Type.UNLOAD, stockholmToHamburg));
		
		final CarrierMovement hamburgToHongKong = new CarrierMovement(new Location("DEHAM"), new Location("CNHKG"));
		
		cargo.handle(new HandlingEvent(getDate("2007-12-03"), HandlingEvent.Type.LOAD, hamburgToHongKong));
		cargo.handle(new HandlingEvent(getDate("2007-12-04"), HandlingEvent.Type.UNLOAD, hamburgToHongKong));
		
		return cargo;
	}

	private Date getDate(String date) throws ParseException {
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.parse(date);
	}

}
