package net.blogjava.dddsample.service;

import org.springframework.transaction.annotation.Transactional;

import net.blogjava.dddsample.domain.Cargo;
import net.blogjava.dddsample.domain.TrackingId;
import net.blogjava.dddsample.repository.CargoRepository;

public class CargoServiceImpl implements CargoService {
	private CargoRepository cargoRepository;

	public void setCargoRepository(CargoRepository repository) {
		this.cargoRepository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public Cargo find(String trackingId) {
		return cargoRepository.find(new TrackingId(trackingId));
	}
	
}
