package net.blogjava.dddsample.domain;

public class Cargo {
	private final TrackingId trackingId;
	private final Location from;
	private final Location to;
	private DeliveryHistory history;

	public Cargo(TrackingId trackingId, Location origin, Location finalDestination) {
		this.trackingId = trackingId;
		this.from = origin;
		this.to = finalDestination;
		
		this.history = new DeliveryHistory();
	}

	public void handle(HandlingEvent event) {
		history.add(event);
	}

	public DeliveryHistory deliveryHistory() {
		return history;
	}

	public boolean atFinalDestiation() {
		return currentLocation().equals(to);
	}

	public Location currentLocation() {
		HandlingEvent lastEvent = history.last();
		CarrierMovement cm = lastEvent.getCarrierMovement();
		return (lastEvent.type() == HandlingEvent.Type.ON) ? cm.from() : cm.to();
	}

	public TrackingId trackingId() {
		return trackingId;
	}

}
