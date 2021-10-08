package com.example.hotel.repositories;

import com.example.hotel.entities.ReservationEntity;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Integer> {
    public List<ReservationEntity> findByClientNomComplet(String search);
    
}
