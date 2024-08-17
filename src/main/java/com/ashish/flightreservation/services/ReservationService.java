package com.ashish.flightreservation.services;

import com.ashish.flightreservation.dto.ReservationRequest;
import com.ashish.flightreservation.entities.Reservation;

public interface ReservationService {

	public Reservation bookFlight(ReservationRequest request);
}
