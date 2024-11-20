package com.hotelprject.hotelproject.service;

import com.hotelprject.hotelproject.model.Reservation;

import java.time.LocalDate;

public interface ReservationService {
    Reservation makeReservation(Long roomId, LocalDate reservationDate, LocalDate endDate, String user);
}
