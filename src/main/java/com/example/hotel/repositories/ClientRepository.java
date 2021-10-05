package com.example.hotel.repositories;

import com.example.hotel.entities.ClientEntity;

import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {
    
}
