package com.example.hotel.repositories;

import com.example.hotel.entities.ReservationEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Integer> {
    public List<ReservationEntity> findByClientNomComplet(String search);
    public List<ReservationEntity> findByDateDebutGreaterThanEqual(Date dateDebut);
    @Query(value = "SELECT numero_chambre from reservation where datedebut >= :startDate AND datefin <= :endDate", nativeQuery = true)
    List<Integer> getAllBetweenDates(@Param("startDate")Date startDate, @Param("endDate")Date endDate);
    
}
