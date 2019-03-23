package net.blogjava.dddsample.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import net.blogjava.dddsample.repository.CargoRepository;
import net.blogjava.dddsample.repository.CargoRepositoryInMem;

public class CargoRepositoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindByCargoId() throws Exception {
		
		CargoRepository cargoRepository = new CargoRepositoryInMem();
		
		final TrackingId trackingId = new TrackingId("XYZ");
		Cargo cargo = cargoRepository.find(trackingId);
		
		assertThat(cargo.trackingId()).isEqualTo(trackingId);
	}

}
