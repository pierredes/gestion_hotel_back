package com.example.hotel.service;

import com.example.hotel.entities.ClientEntity;
import com.example.hotel.entities.HotelEntity;
import com.example.hotel.entities.ReservationEntity;
import com.example.hotel.repositories.ClientRepository;
import com.example.hotel.repositories.HotelRepository;
import com.example.hotel.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository rr;

    public ReservationService(ReservationRepository rr) {
        this.rr = rr;
    }

    public List<ReservationEntity> getAllReservation(String search) {
        if (search == null || search.length() == 0) {
            return (List<ReservationEntity>) rr.findAll();
        } else {
            return (List<ReservationEntity>) rr.findByClientNomComplet(search);
        }

    }

    public ReservationEntity getReservationById(int id) {
        return rr.findById(id).get();
    }

    public ReservationEntity addReservation(String dateDebut, String dateFin, int numeroChambre, int client, int hotel) {
        SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat formatDateRecherche = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date dateDebutFormatter = formatterDate.parse(dateDebut);
            Date dateFinFormater = formatterDate.parse(dateFin);
            Date dateDebutRecherche = formatDateRecherche.parse(dateDebut);
            Date dateFinRecherche = formatDateRecherche.parse(dateFin);
            List<Integer> test = rr.getAllBetweenDates(dateDebutRecherche, dateFinRecherche);
            for (Integer i: test) {
                if (numeroChambre == i) {
                    throw new Exception(" numéro déjà pris");
                }
            }
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

    public ReservationEntity updateReservation(int id, String dateDebut, String dateFin, int numeroChambre, int client, int hotel) {
        SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat formatDateRecherche = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateDebutFormatter = formatterDate.parse(dateDebut);
            Date dateFinFormater = formatterDate.parse(dateFin);
            Date dateDebutRecherche = formatDateRecherche.parse(dateDebut);
            Date dateFinRecherche = formatDateRecherche.parse(dateFin);
            List<Integer> test = rr.getAllBetweenDates(dateDebutRecherche, dateFinRecherche);
            for (Integer i: test) {
                if (numeroChambre == i) {
                    throw new Exception(" numéro déjà pris");
                }
            }
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
