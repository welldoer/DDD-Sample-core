package net.blogjava.dddsample.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.blogjava.dddsample.domain.Cargo;
import net.blogjava.dddsample.service.CargoService;

@Controller
public class CargoTrackingController {
	@Autowired
	private CargoService cargoService;

	@RequestMapping(value="/start")
	public ModelAndView getTrackingId() {
		ModelAndView modelAndView = new ModelAndView("start", "trackCommand", new TrackCommand());
		
		return modelAndView;
	}
	
	@RequestMapping(value="/result")
	public ModelAndView processTrackingId(TrackCommand trackCommand) {
		Cargo cargo = cargoService.find(trackCommand.getTrackingId());
		
		ModelAndView modelAndView = new ModelAndView("cargo");
		if(cargo != null)
			modelAndView.addObject("location", cargo.currentLocation());

		return modelAndView;
	}
}
