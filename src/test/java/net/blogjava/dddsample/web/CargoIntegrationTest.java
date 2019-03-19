package net.blogjava.dddsample.web;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.blogjava.dddsample.service.CargoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class CargoIntegrationTest {
	
	@Autowired
	private CargoService cargoService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCargoServiceShouldBeInjected() {
		assertThat(cargoService).isNotNull();
	}

}
