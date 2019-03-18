package net.blogjava.dddsample.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CargoRepositoryTest {

	private static final String CTX = "applicationContext.xml";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindByCargoId() {
		
		CargoRepository repository = getCargoRepository();
		final TrackingId trackingId = new TrackingId("XYZ");
		Cargo cargo = repository.find(trackingId);
		
		assertThat(cargo.trackingId()).isEqualTo(trackingId);
	}

	private CargoRepository getCargoRepository() {
		return (CargoRepository)getBean("cargoRepository");
	}

	private Object getBean(String bean) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {CTX});
		return context.getBean(bean);
	}
	
	

}
