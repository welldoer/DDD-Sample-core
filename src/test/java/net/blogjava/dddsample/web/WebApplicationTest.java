package net.blogjava.dddsample.web;

import static org.assertj.core.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/test/webapp")
@ContextConfiguration("classpath:/springmvc-servlet.xml")
public class WebApplicationTest {
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
	public void testWebApp() {
		assertThat(applicationContext).isNotNull();
		assertThat(servletContext).isNotNull();
	}
	
	@Test
	public void helloReaderControllerWorksOk() throws Exception {
		mockMvc.perform(get("/hello"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("message", "Hello Reader!"))
				.andExpect(view().name("helloReader"))
				.andDo(print());
	}

}
