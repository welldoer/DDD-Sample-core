package net.blogjava.dddsample.domain;

public class Cargo {

	public Cargo(TrackingId trackingId, Location origin, Location finalDestination) {
	}

	public void handle(HandlingEvent event) {
	}

	public DeliveryHistory deliveryHistory() {
		return new DeliveryHistory();
	}

	public boolean atFinalDestiation() {
		return false;
	}

	public Location currentLocation() {
		return new Location("CNHKG");
	}

	public TrackingId trackingId() {
		return null;
	}

}
