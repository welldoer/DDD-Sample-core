package net.blogjava.dddsample.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class CargoRepositoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindByCargoId() {
		CargoRepository repository = new CargoRepositoryImpl();
		final TrackingId trackingId = new TrackingId("XYZ");
		Cargo cargo = repository.find(trackingId);
		
//		assertThat(cargo.trackingId()).isEqualTo(trackingId);
	}

}
