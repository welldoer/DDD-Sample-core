package net.blogjava.dddsample.web;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Map;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import net.blogjava.dddsample.domain.Cargo;
import net.blogjava.dddsample.domain.Location;
import net.blogjava.dddsample.domain.TrackingId;
import net.blogjava.dddsample.repository.CargoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/test/webapp")
@ContextConfiguration("classpath:/springmvc-servlet.xml")
public class CargoControllerTest {
	@Autowired
	private WebApplicationContext applicationContext;
	
	@Autowired
	private MockServletContext servletContext;
	
	private MockMvc mockMvc;
	
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	
	@Mock
	private Map<String, Cargo> mockCargoDb;
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.applicationContext).build();
	}

	@Test
	public void testCargoPathOk() throws Exception {
		mockMvc.perform(get("/cargo"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("location", new Location("CNHKG")))
		.andExpect(view().name("cargo"))
		.andDo(print());
	}

	@Test
	public void testStartPathOk() throws Exception {
		mockMvc.perform(get("/start"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("trackCommand", new TrackCommand()))
			.andExpect(view().name("start"));
	}
	
	@Test
	public void testResultPostNotFound() throws Exception {
		mockMvc.perform(post("/result").param("trackingId", "abc"))
			.andExpect(status().isOk())
			.andExpect(view().name("start"))
			.andExpect(model().attribute("trackCommand", new TrackCommand()))
			.andExpect(model().attribute("trackingId", "abc"))
			.andExpect(model().attributeDoesNotExist("cargo"));
	}

	@Test
	public void testResultPostOk() throws Exception {
		CargoRepository cargoRepository = (CargoRepository) applicationContext.getBean("cargoRepository");
		mockMvc.perform(post("/result").param("trackingId", "XYZ"))
			.andExpect(status().isOk())
			.andExpect(view().name("start"))
			.andExpect(model().attribute("cargo", cargoRepository.find(new TrackingId("XYZ"))));
	}

	@Test
	public void testResultPostMock() throws Exception {
		CargoRepository cargoRepository = (CargoRepository) applicationContext.getBean("cargoRepository");

		ReflectionTestUtils.setField(cargoRepository, "cargoDb", mockCargoDb);
//		doReturn(new Cargo(new TrackingId("123"), new Location("456"), new Location("789"))).when(mockCargoDb).get(any());
		Cargo cargo = new Cargo(new TrackingId("Track123"), new Location("L456"), new Location("L789"));
		when(mockCargoDb.get(any())).thenReturn(cargo);
		
		mockMvc.perform(post("/result").param("trackingId", "XYZ"))
			.andExpect(status().isOk())
			.andExpect(view().name("start"))
			.andExpect(model().attribute("cargo", cargo));
	}


}
