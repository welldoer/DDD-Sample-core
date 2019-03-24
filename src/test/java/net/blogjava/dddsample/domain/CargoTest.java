package net.blogjava.dddsample.domain;

import static org.assertj.core.api.Assertions.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class CargoTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCurrentLocationAtOrigin() throws Exception {
	    Location destination = new Location("AUMEL");
	    Location origin = new Location("SESTO");
	    Cargo cargo = new Cargo(new TrackingId("XYZ"), origin, destination);

	    assertThat(cargo.getCurrentLocation()).isEqualTo(origin);
	}
	
	@Test
	public void testCurrentLocationUnloaded() throws Exception {
		Cargo cargo = populateCargoOffHongKong();
		
		assertThat(cargo.getCurrentLocation()).isEqualTo(new Location("CNHKG"));
	}
	
	@Test
	public void testCurrentLocationloaded() throws Exception {
		Cargo cargo = populateCargoOnHamburg();
		
		assertThat(cargo.getCurrentLocation()).isEqualTo(new Location("DEHAM"));
	}

	@Test
	public void testAtFinalLocation() throws Exception {
		Cargo cargo = populateCargoOffMelbourne();
		
		assertThat(cargo.atFinalDestiation()).isTrue();
	}

	@Test
	public void testNotAtFinalLocationWhenNotUnloaded() throws Exception {
		Cargo cargo = populateCargoOnHongKong();
		
		assertThat(cargo.atFinalDestiation()).isFalse();
	}

	private Cargo populateCargoOffHongKong() throws Exception {
		final Cargo cargo = new Cargo(new TrackingId("XYZ"), new Location("SESTO"), new Location("AUMEL"));
		
		final CarrierMovement stockholmToHamburg = new CarrierMovement(new Location("SESTO"), new Location("DEHAM"));
		
		cargo.handle(new HandlingEvent(getDate("2007-12-01"), HandlingEvent.Type.LOAD, stockholmToHamburg));
		cargo.handle(new HandlingEvent(getDate("2007-12-02"), HandlingEvent.Type.UNLOAD, stockholmToHamburg));
		
		final CarrierMovement hamburgToHongKong = new CarrierMovement(new Location("DEHAM"), new Location("CNHKG"));
		
		cargo.handle(new HandlingEvent(getDate("2007-12-03"), HandlingEvent.Type.LOAD, hamburgToHongKong));
		cargo.handle(new HandlingEvent(getDate("2007-12-04"), HandlingEvent.Type.UNLOAD, hamburgToHongKong));
		
		return cargo;
	}

	private Cargo populateCargoOnHamburg() throws Exception {
		final Cargo cargo = new Cargo(new TrackingId("XYZ"), new Location("SESTO"), new Location("AUMEL"));
		
		final CarrierMovement stockholmToHamburg = new CarrierMovement(new Location("SESTO"), new Location("DEHAM"));
		
		cargo.handle(new HandlingEvent(getDate("2007-12-01"), HandlingEvent.Type.LOAD, stockholmToHamburg));
		cargo.handle(new HandlingEvent(getDate("2007-12-02"), HandlingEvent.Type.UNLOAD, stockholmToHamburg));
		
		final CarrierMovement hamburgToHongKong = new CarrierMovement(new Location("DEHAM"), new Location("CNHKG"));
		
		cargo.handle(new HandlingEvent(getDate("2007-12-03"), HandlingEvent.Type.LOAD, hamburgToHongKong));
		
		return cargo;
	}

	private Cargo populateCargoOffMelbourne() throws Exception {
		final Cargo cargo = new Cargo(new TrackingId("XYZ"), new Location("SESTO"), new Location("AUMEL"));
		
		final CarrierMovement stockholmToHamburg = new CarrierMovement(new Location("SESTO"), new Location("DEHAM"));
		
		cargo.handle(new HandlingEvent(getDate("2007-12-01"), HandlingEvent.Type.LOAD, stockholmToHamburg));
		cargo.handle(new HandlingEvent(getDate("2007-12-02"), HandlingEvent.Type.UNLOAD, stockholmToHamburg));
		
		final CarrierMovement hamburgToHongKong = new CarrierMovement(new Location("DEHAM"), new Location("CNHKG"));
		
		cargo.handle(new HandlingEvent(getDate("2007-12-03"), HandlingEvent.Type.LOAD, hamburgToHongKong));
		cargo.handle(new HandlingEvent(getDate("2007-12-04"), HandlingEvent.Type.UNLOAD, hamburgToHongKong));
		
		final CarrierMovement hongKongToMelbourne = new CarrierMovement(new Location("CNHKG"), new Location("AUMEL"));
		
		cargo.handle(new HandlingEvent(getDate("2007-12-05"), HandlingEvent.Type.LOAD, hongKongToMelbourne));
		cargo.handle(new HandlingEvent(getDate("2007-12-07"), HandlingEvent.Type.UNLOAD, hongKongToMelbourne));
		
		return cargo;
	}

	private Cargo populateCargoOnHongKong() throws Exception {
		final Cargo cargo = new Cargo(new TrackingId("XYZ"), new Location("SESTO"), new Location("AUMEL"));
		
		final CarrierMovement stockholmToHamburg = new CarrierMovement(new Location("SESTO"), new Location("DEHAM"));
		
		cargo.handle(new HandlingEvent(getDate("2007-12-01"), HandlingEvent.Type.LOAD, stockholmToHamburg));
		cargo.handle(new HandlingEvent(getDate("2007-12-02"), HandlingEvent.Type.UNLOAD, stockholmToHamburg));
		
		final CarrierMovement hamburgToHongKong = new CarrierMovement(new Location("DEHAM"), new Location("CNHKG"));
		
		cargo.handle(new HandlingEvent(getDate("2007-12-03"), HandlingEvent.Type.LOAD, hamburgToHongKong));
		cargo.handle(new HandlingEvent(getDate("2007-12-04"), HandlingEvent.Type.UNLOAD, hamburgToHongKong));
		
		final CarrierMovement hongKongToMelbourne = new CarrierMovement(new Location("CNHKG"), new Location("AUMEL"));
		
		cargo.handle(new HandlingEvent(getDate("2007-12-05"), HandlingEvent.Type.LOAD, hongKongToMelbourne));
		
		return cargo;
	}

	private Date getDate(String date) throws ParseException {
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.parse(date);
	}

}
