package com.ashish.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

	
}
