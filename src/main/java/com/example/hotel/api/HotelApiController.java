package com.example.hotel.api;

import com.example.hotel.entities.HotelEntity;
import com.example.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/ws/hotel")
public class HotelApiController {

    @Autowired
    HotelService hs;

    public static boolean isEmailValid(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }

    @GetMapping(path = "/", produces = "application/json")
    public List<HotelEntity> getAllHotel() {
        return hs.getAllHotel();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<HotelEntity> getAllHotel(@PathVariable(name = "id") int id) {
        HotelEntity hotel = hs.getHotelById(id);
        if (hotel != null) {
            return ResponseEntity.ok().body(hotel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/", produces = "application/json")
    public ResponseEntity<HotelEntity> addHotel(@RequestBody HotelEntity hotel) {
        try {
            if (isEmailValid(hotel.getEmail())) {
                HotelEntity createHotel = hs.addHotel(hotel.getNom(), hotel.getEtoiles(), hotel.getAdresse(), hotel.getTelephone(), hotel.getEmail(), hotel.getVille());
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createHotel.getId()).toUri();
                return ResponseEntity.created(uri).body(createHotel);
            } else {
                throw new Exception("Email non Valide !!");
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e);
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() );
        }
    }

    @PutMapping(path = "/update/{id}", produces = "application/json")
    public ResponseEntity<HotelEntity> updateHotel(@PathVariable(name = "id") int id, @RequestBody HotelEntity hotel) {

        try {
            if (isEmailValid(hotel.getEmail())) {
                HotelEntity updateHotel = hs.updateHotel(id, hotel.getNom(), hotel.getEtoiles(), hotel.getAdresse(), hotel.getTelephone(), hotel.getEmail(), hotel.getVille());
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updateHotel).toUri();
                return ResponseEntity.created(uri).body(updateHotel);
            } else {
                throw new Exception("Email non Valide !!");
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e);
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() );
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> deleteHotelById(@PathVariable(name = "id") int id) {
        try {
            hs.deleteHotelById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
            return ResponseEntity.notFound().build();
        }
    }
}
