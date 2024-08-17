package com.ashish.flightreservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashish.flightreservation.dto.ReservationRequest;
import com.ashish.flightreservation.entities.Flight;
import com.ashish.flightreservation.entities.Passenger;
import com.ashish.flightreservation.entities.Reservation;
import com.ashish.flightreservation.repos.FlightRepository;
import com.ashish.flightreservation.repos.PassengerRepository;
import com.ashish.flightreservation.repos.ReservationRepository;
import com.ashish.flightreservation.util.EmailUtil;
import com.ashish.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired 
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Override
	public Reservation bookFlight(ReservationRequest request) {
		//Make Payment
		Long flightId = request.getFlightId();
		
		Flight flight = flightRepository.findById(flightId).get();
		
		System.out.println(flight.getId());
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		Reservation savedReservation = reservationRepository.save(reservation);
		
		
		String filePath = "C:/Users/hp/OneDrive/Desktop/reservation"+savedReservation.getId()+".pdf";
		pdfGenerator.generateItinerary(savedReservation, filePath);
		emailUtil.sendItinerary(passenger.getEmail(), filePath);
		return savedReservation;
	}

}
