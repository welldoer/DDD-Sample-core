package net.blogjava.dddsample.repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.blogjava.dddsample.domain.Cargo;
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

		cargoXYZ.handle(new HandlingEvent(getDate("2007-11-30"), HandlingEvent.Type.RECEIVE, null));
		
		final CarrierMovement stockholmToHamburg = new CarrierMovement(new Location("SESTO"), new Location("DEHAM"));
		
		cargoXYZ.handle(new HandlingEvent(getDate("2007-12-01"), HandlingEvent.Type.LOAD, stockholmToHamburg));
		cargoXYZ.handle(new HandlingEvent(getDate("2007-12-02"), HandlingEvent.Type.UNLOAD, stockholmToHamburg));
		
		final CarrierMovement hamburgToHongKong = new CarrierMovement(new Location("DEHAM"), new Location("CNHKG"));
		
		cargoXYZ.handle(new HandlingEvent(getDate("2007-12-03"), HandlingEvent.Type.LOAD, hamburgToHongKong));
		cargoXYZ.handle(new HandlingEvent(getDate("2007-12-04"), HandlingEvent.Type.UNLOAD, hamburgToHongKong));
		
		cargoDb.put(trackIdXYZ, cargoXYZ);
		
	    String trackIdZYX = "ZYX";
	    final Cargo cargoZYX = new Cargo(new TrackingId(trackIdZYX), new Location("AUMEL"), new Location("SESTO"));
	
	    cargoZYX.handle(new HandlingEvent(getDate("2007-12-09"), HandlingEvent.Type.RECEIVE, null));
	    
	    final CarrierMovement melbourneToTokyo = new CarrierMovement(new Location("AUMEL"), new Location("JPTOK"));
	
	    cargoZYX.handle(new HandlingEvent(getDate("2007-12-10"), HandlingEvent.Type.LOAD, melbourneToTokyo));
	    cargoZYX.handle(new HandlingEvent(getDate("2007-12-12"), HandlingEvent.Type.UNLOAD, melbourneToTokyo));
	
	    final CarrierMovement tokyoToLosAngeles = new CarrierMovement(new Location("JPTOK"), new Location("USLA"));
	
	    cargoZYX.handle(new HandlingEvent(getDate("2007-12-13"), HandlingEvent.Type.LOAD, tokyoToLosAngeles));
	
	    cargoDb.put(trackIdZYX, cargoZYX);
	    
	    String trackIdABC = "ABC";
	    final Cargo cargoABC = new Cargo(new TrackingId(trackIdABC), new Location("SESTO"), new Location("FIHEL"));
	
	    cargoABC.handle(new HandlingEvent(getDate("2008-01-01"), HandlingEvent.Type.RECEIVE, null));
	
	    final CarrierMovement stockholmToHelsinki = new CarrierMovement(new Location("SESTO"), new Location("FIHEL"));
	
	    cargoABC.handle(new HandlingEvent(getDate("2008-01-02"), HandlingEvent.Type.LOAD, stockholmToHelsinki));
	    cargoABC.handle(new HandlingEvent(getDate("2008-01-03"), HandlingEvent.Type.UNLOAD, stockholmToHelsinki));
	    cargoABC.handle(new HandlingEvent(getDate("2008-01-05"), HandlingEvent.Type.CLAIM, null));
	
	    cargoDb.put(trackIdABC, cargoABC);
	
	    String trackIdCBA = "CBA";
	    final Cargo cargoCBA = new Cargo(new TrackingId(trackIdCBA), new Location("FIHEL"), new Location("SESTO"));
	
	    cargoCBA.handle(new HandlingEvent(getDate("2008-01-10"), HandlingEvent.Type.RECEIVE, null));
	
	
	    cargoDb.put(trackIdCBA, cargoCBA);
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
