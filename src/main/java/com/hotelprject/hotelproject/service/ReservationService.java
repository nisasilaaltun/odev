package com.hotelprject.hotelproject.service;

import com.hotelprject.hotelproject.model.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservations();
    Reservation getReservationById(Long id);
    Reservation saveReservation(Reservation reservation);
    void deleteReservation(Long id);
    Reservation makeReservation(Long roomId, LocalDate reservationDate, LocalDate endDate, String user);
}
