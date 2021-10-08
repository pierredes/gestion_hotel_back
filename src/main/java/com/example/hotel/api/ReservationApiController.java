package com.example.hotel.api;

import com.example.hotel.entities.ReservationEntity;
import com.example.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/ws/reservation")
public class ReservationApiController {

    @Autowired
    ReservationService rs;

    @GetMapping(path = "/", produces = "application/json")
    public List<ReservationEntity> getAllReservation(HttpServletRequest request) {

        return rs.getAllReservation(request.getParameter("search"));
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<ReservationEntity> getReservationById(@PathVariable(name = "id") int id) {
        ReservationEntity reservation = rs.getReservationById(id);
        if (reservation != null) {
            return ResponseEntity.ok().body(reservation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/", produces = "application/json")
    public ResponseEntity<ReservationEntity> addReservation(@RequestBody ReservationEntity reservation) {
        SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        String dateDebutToString = formatterDate.format(reservation.getDateDebut());
        String dateFinToString = formatterDate.format(reservation.getDateFin());
        try {
            if (dateDebutToString.compareTo(dateFinToString) < 0) {
                ReservationEntity createReservation = rs.addReservation(dateDebutToString, dateFinToString, reservation.getNumeroChambre(),reservation.getClient().getId(), reservation.getHotel().getId());
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createReservation.getId()).toUri();
                return ResponseEntity.created(uri).body(createReservation);
            } else {
                throw new Exception("votre date de départ est avant celle d'arrivé");
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e);
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() );
        }
    }

    @PutMapping(path = "/update/{id}", produces = "application/json")
    public ResponseEntity<ReservationEntity> updateReservation(@PathVariable(name = "id") int id , @RequestBody ReservationEntity reservation) {
        SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        String dateDebutToString = formatterDate.format(reservation.getDateDebut());
        String dateFinToString = formatterDate.format(reservation.getDateFin());
        try {
            if (dateDebutToString.compareTo(dateFinToString) < 0) {
                ReservationEntity updateReservation = rs.updateReservation(id, dateDebutToString, dateFinToString, reservation.getNumeroChambre(), reservation.getClient().getId(), reservation.getHotel().getId());
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updateReservation).toUri();
                return ResponseEntity.created(uri).body(updateReservation);
            } else {
                throw new Exception("votre date de départ est avant celle d'arrivé");
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e);
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() );
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> deleteReservationById(@PathVariable(name = "id") int id) {
        try {
            rs.deleteReservationById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
            return ResponseEntity.notFound().build();
        }
    }
}
