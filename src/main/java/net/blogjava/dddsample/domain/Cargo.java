package net.blogjava.dddsample.domain;

public class Cargo {
	private final TrackingId trackingId;
	private final Location origin;
	private final Location finalDestination;
	private DeliveryHistory history;

	public Cargo(TrackingId trackingId, Location origin, Location finalDestination) {
		this.trackingId = trackingId;
		this.origin = origin;
		this.finalDestination = finalDestination;
		
		this.history = new DeliveryHistory();
	}

	public void handle(HandlingEvent event) {
		history.addEvent(event);
	}

	public DeliveryHistory getDeliveryHistory() {
		return history;
	}

	public boolean atFinalDestiation() {
		return getCurrentLocation().equals(finalDestination);
	}

	public Location getCurrentLocation() {
		HandlingEvent lastEvent = history.last();
		if(lastEvent == null)
			return origin;

		return lastEvent.getLocation();
	}

	public TrackingId trackingId() {
		return trackingId;
	}

}
