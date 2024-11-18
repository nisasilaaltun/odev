package com.hotelprject.hotelproject.service;

import com.hotelprject.hotelproject.model.HotelUser;

import java.util.Optional;

public interface HotelUserService {

    boolean existsByEmail(String email);
    void save(HotelUser hotelUser);
    Optional<HotelUser> findByEmailAndPassword(String email, String password);
}
