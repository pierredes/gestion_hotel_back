package com.example.hotel.service;

import com.example.hotel.entities.ClientEntity;
import com.example.hotel.entities.HotelEntity;
import com.example.hotel.entities.ReservationEntity;
import com.example.hotel.repositories.ClientRepository;
import com.example.hotel.repositories.HotelRepository;
import com.example.hotel.repositories.ReservationRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReservationService {
    private final ReservationRepository rr;

    public ReservationService(ReservationRepository rr) {
        this.rr = rr;
    }

    public List<ReservationEntity> getAllReservation() {
        return (List<ReservationEntity>) rr.findAll();
    }

    public ReservationEntity getReservationById(int id) {
        return rr.findById(id).get();
    }

    public ReservationEntity addReservation(String dateDebut, String dateFin, int numeroChambre, int client, int hotel) {
        SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        try {
            Date dateDebutFormatter = formatterDate.parse(dateDebut);
            Date dateFinFormater = formatterDate.parse(dateFin);
            ReservationEntity reservation = new ReservationEntity();
            reservation.setDateDebut(dateDebutFormatter);
            reservation.setDateFin(dateFinFormater);
            reservation.setNumeroChambre(numeroChambre);
            ClientEntity client1 = new ClientEntity();
            client1.setId(client);
            reservation.setClient(client1);
            HotelEntity hotel1 = new HotelEntity();
            hotel1.setId(hotel);
            reservation.setHotel(hotel1);
            rr.save(reservation);
            return  reservation;
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
            return null;
        }
    }

    public ReservationEntity addReservation(int id, String dateDebut, String dateFin, int numeroChambre, int client, int hotel) {
        SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        try {
            Date dateDebutFormatter = formatterDate.parse(dateDebut);
            Date dateFinFormater = formatterDate.parse(dateFin);
            ReservationEntity reservation = this.getReservationById(id);
            reservation.setDateDebut(dateDebutFormatter);
            reservation.setDateFin(dateFinFormater);
            reservation.setNumeroChambre(numeroChambre);
            ClientEntity client1 = new ClientEntity();
            client1.setId(client);
            reservation.setClient(client1);
            HotelEntity hotel1 = new HotelEntity();
            hotel1.setId(hotel);
            reservation.setHotel(hotel1);
            rr.save(reservation);
            return  reservation;
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
            return null;
        }
    }

    public void deleteReservationById(int id) {
        try {
            rr.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
    }
}
