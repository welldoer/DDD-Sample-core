package net.blogjava.dddsample.repository;

import net.blogjava.dddsample.domain.Cargo;
import net.blogjava.dddsample.domain.TrackingId;

public interface CargoRepository {

	Cargo find(TrackingId trackingId);

}
