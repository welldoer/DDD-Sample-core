package net.blogjava.dddsample.domain;

public interface CargoRepository {

	Cargo find(TrackingId trackingId);

}
