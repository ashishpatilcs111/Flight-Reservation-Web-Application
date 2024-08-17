package com.ashish.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	
}
