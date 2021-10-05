package com.example.hotel.service;

import com.example.hotel.entities.HotelEntity;
import com.example.hotel.repositories.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    private final HotelRepository hr;

    public HotelService(HotelRepository hr) {
        this.hr = hr;
    }

    public List<HotelEntity> getAllHotel() {
        return (List<HotelEntity>) hr.findAll();
    }

    public HotelEntity getHotelById(int id) {
        return hr.findById(id).get();
    }

    public HotelEntity addHotel(String nom, int etoiles, String adresse, String telephone, String email, String ville) {
        try {
            HotelEntity hotel = new HotelEntity();
            hotel.setNom(nom);
            hotel.setEtoiles(etoiles);
            hotel.setAdresse(adresse);
            hotel.setTelephone(telephone);
            hotel.setEmail(email);
            hotel.setVille(ville);
            hr.save(hotel);
            return hotel;
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
            return null;
        }
    }

    public HotelEntity updateHotel(int id, String nom, int etoiles, String adresse, String telephone, String email, String ville) {
        try {
            HotelEntity hotel = this.getHotelById(id);
            hotel.setNom(nom);
            hotel.setEtoiles(etoiles);
            hotel.setAdresse(adresse);
            hotel.setTelephone(telephone);
            hotel.setEmail(email);
            hotel.setVille(ville);
            hr.save(hotel);
            return hotel;
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
            return null;
        }
    }

    public void deleteHotelById(int id) {
        try {
            hr.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
    }


}
