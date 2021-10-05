package com.example.hotel.repositories;

import com.example.hotel.entities.ReservationEntity;

import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Integer> {
    
}
