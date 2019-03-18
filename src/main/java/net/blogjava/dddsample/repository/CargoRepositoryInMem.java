package net.blogjava.dddsample.repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.blogjava.dddsample.domain.Cargo;
import net.blogjava.dddsample.domain.CargoRepository;
import net.blogjava.dddsample.domain.CarrierMovement;
import net.blogjava.dddsample.domain.HandlingEvent;
import net.blogjava.dddsample.domain.Location;
import net.blogjava.dddsample.domain.TrackingId;

public class CargoRepositoryInMem implements CargoRepository {
	private Map<String, Cargo> cargoDb;

	public CargoRepositoryInMem() throws Exception {
		cargoDb = new HashMap<>();
		setup();
	}
	
	private void setup() throws Exception {
		String trackIdXYZ = "XYZ";
		final Cargo cargoXYZ = new Cargo(new TrackingId(trackIdXYZ), new Location("SESTO"), new Location("AUMEL"));

		final CarrierMovement stockholmToHamburg = new CarrierMovement(new Location("SESTO"), new Location("DEHAM"));
		
		cargoXYZ.handle(new HandlingEvent(getDate("2007-12-01"), HandlingEvent.Type.ON, stockholmToHamburg));
		cargoXYZ.handle(new HandlingEvent(getDate("2007-12-02"), HandlingEvent.Type.OFF, stockholmToHamburg));
		
		final CarrierMovement hamburgToHongKong = new CarrierMovement(new Location("DEHAM"), new Location("CNHKG"));
		
		cargoXYZ.handle(new HandlingEvent(getDate("2007-12-03"), HandlingEvent.Type.ON, hamburgToHongKong));
		cargoXYZ.handle(new HandlingEvent(getDate("2007-12-04"), HandlingEvent.Type.OFF, hamburgToHongKong));
		
		cargoDb.put(trackIdXYZ, cargoXYZ);
	}

	private Date getDate(String date) throws ParseException {
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.parse(date);
	}

	@Override
	public Cargo find(TrackingId trackingId) {
		return cargoDb.get(trackingId.getId());
	}

}