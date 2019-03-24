package net.blogjava.dddsample.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.blogjava.dddsample.domain.Cargo;
import net.blogjava.dddsample.service.CargoService;

@Controller
public class DispatchController {
	@Autowired
	private CargoService cargoService;

	@RequestMapping(value="/cargo")
	public ModelAndView doCargo() {
		Cargo cargo = cargoService.find("XYZ");
		
		ModelAndView mv = new ModelAndView("cargo");
		mv.addObject("location", cargo.getCurrentLocation());
		
		return mv;
	}
	
}
