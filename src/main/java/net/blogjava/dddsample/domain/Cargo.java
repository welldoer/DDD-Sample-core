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

	public DeliveryHistory deliveryHistory() {
		return history;
	}

	public boolean atFinalDestiation() {
		return currentLocation().equals(finalDestination);
	}

	public Location currentLocation() {
		HandlingEvent lastEvent = history.last();
		if(lastEvent == null)
			return origin;
		
		CarrierMovement cm = lastEvent.getCarrierMovement();
		return (lastEvent.type() == HandlingEvent.Type.ON) ? cm.from() : cm.to();
	}

	public TrackingId trackingId() {
		return trackingId;
	}

}
