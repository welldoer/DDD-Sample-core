package net.blogjava.dddsample.web;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import net.blogjava.dddsample.domain.Location;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/test/webapp")
@ContextConfiguration("classpath:/springmvc-servlet.xml")
public class CargoControllerTest {
	@Autowired
	private WebApplicationContext applicationContext;
	
	@Autowired
	private MockServletContext servletContext;
	
	private MockMvc mockMvc;

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
			.andExpect(view().name("cargo"))
			.andExpect(model().attributeDoesNotExist("location"));
	}

	@Test
	public void testResultPostOk() throws Exception {
		mockMvc.perform(post("/result").param("trackingId", "XYZ"))
			.andExpect(status().isOk())
			.andExpect(view().name("cargo"))
			.andExpect(model().attribute("location", new Location("CNHKG")));
	}

}
