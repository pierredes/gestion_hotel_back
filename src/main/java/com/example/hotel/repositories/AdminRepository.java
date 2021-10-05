package com.example.hotel.repositories;

import com.example.hotel.entities.AdminEntity;

import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<AdminEntity, Integer> {
    
}
