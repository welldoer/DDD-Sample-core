package net.blogjava.dddsample.service;

import net.blogjava.dddsample.domain.Cargo;
import net.blogjava.dddsample.domain.CargoRepository;
import net.blogjava.dddsample.domain.TrackingId;

public class CargoServiceImpl implements CargoService {
	private CargoRepository repository;

	@Override
	public Cargo find(String trackingId) {
		return getCargoRepository().find(new TrackingId(trackingId));
	}
	
	public CargoRepository getCargoRepository() {
		return repository;
	}
	
	public void setCargoRepository(CargoRepository repository) {
		this.repository = repository;
	}

}
