package com.example.hotel.repositories;

import com.example.hotel.entities.HotelEntity;

import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<HotelEntity, Integer> {
    
}
