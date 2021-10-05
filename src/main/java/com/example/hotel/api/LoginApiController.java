package com.example.hotel.api;

import com.example.hotel.entities.AdminEntity;
import com.example.hotel.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/ws/login")
public class LoginApiController {
    @Autowired
    private AdminRepository ar;

    @PostMapping(path = "/", produces = "application/json")
    ResponseEntity<AdminEntity> login(@RequestBody AdminEntity admin) {
        try {
            AdminEntity admin1 = ar.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
            return ResponseEntity.ok().body(admin1);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
