package net.blogjava.dddsample.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.blogjava.dddsample.domain.Cargo;
import net.blogjava.dddsample.domain.TrackingId;

public class CargoServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCargoServiceFindByTrackingIdScenario() {
		CargoService service = getCargoService();
		
		Cargo cargo = service.find("XYZ");
		
		assertThat(cargo.trackingId()).isEqualTo(new TrackingId("XYZ"));
	}

	private CargoService getCargoService() {
		return (CargoService)getBean("cargoService");
	}

	private Object getBean(String bean) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		return context.getBean(bean);
	}

}
