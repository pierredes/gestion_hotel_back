package com.example.hotel.api;

import com.example.hotel.entities.ClientEntity;
import com.example.hotel.service.ClientService;
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
@RequestMapping("/ws/client")
public class ClientApiController {

    @Autowired
    ClientService cs;

    public static boolean isEmailValid(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }

    @GetMapping(path = "/", produces = "application/json")
    public List<ClientEntity> getAllClient() {
        return  cs.getAllClient();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<ClientEntity> getClientById(@PathVariable(name = "id") int id) {
        ClientEntity client = cs.getClientById(id);
        if ( client != null ) {
            return ResponseEntity.ok().body(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/", produces = "application/json")
    public ResponseEntity<ClientEntity> addClient(@RequestBody ClientEntity client) {
        try {
            if (isEmailValid(client.getEmail())) {
                ClientEntity createClient = cs.addPatient(client.getNomComplet(), client.getTelephone(), client.getEmail(), client.getAdresse());
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createClient.getId()).toUri();
                return ResponseEntity.created(uri).body(createClient);
            } else {
                throw new Exception("Email non Valide !!");
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e);
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() );
        }
    }

    @PutMapping(path = "/update/{id}", produces = "application/json")
    public ResponseEntity<ClientEntity> updateClient(@PathVariable(name = "id") int id, @RequestBody ClientEntity client) {
        try {
            if (isEmailValid(client.getEmail())) {
                ClientEntity updateClient = cs.updadeClient(id, client.getNomComplet(), client.getTelephone(), client.getEmail(), client.getAdresse());
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updateClient).toUri();
                return ResponseEntity.created(uri).body(updateClient);
            } else {
                throw new Exception("Email non Valide !!");
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e);
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() );
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> deleteClientById(@PathVariable(name = "id") int id) {
        try {
            cs.deleteClientById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
            return ResponseEntity.badRequest().build();
        }
    }
}
