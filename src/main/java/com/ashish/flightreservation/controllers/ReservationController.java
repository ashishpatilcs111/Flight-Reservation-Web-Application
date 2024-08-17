package com.ashish.flightreservation.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashish.flightreservation.dto.ReservationRequest;
import com.ashish.flightreservation.entities.Flight;
import com.ashish.flightreservation.entities.Reservation;
import com.ashish.flightreservation.repos.FlightRepository;
import com.ashish.flightreservation.services.ReservationService;

@Controller
public class ReservationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	ReservationService reservationService;
	
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
		
		Flight flight = flightRepository.findById(flightId).get();
		modelMap.addAttribute("flight", flight);
		return "completeReservation";
	}
	
	@RequestMapping(value="/completeReservation", method = RequestMethod.POST) 
	public String completeReservation(ReservationRequest request, ModelMap modelMap) {
		System.out.println(request.getFlightId());
		Reservation reservation = reservationService.bookFlight(request);
		modelMap.addAttribute("msg", "reservation created success and the id is "+reservation.getId());
		return "reservationConfirmation";

	}

}
