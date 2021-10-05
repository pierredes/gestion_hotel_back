package com.example.hotel.service;

import com.example.hotel.entities.AdminEntity;
import com.example.hotel.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final AdminRepository ar;

    public AdminService(AdminRepository ar) {
        this.ar = ar;
    }

    public List<AdminEntity> getAllAdmin() {
        return (List<AdminEntity>) ar.findAll();
    }

    public AdminEntity getAdminById(int id) {
        return ar.findById(id).get();
    }

    public AdminEntity addAdmin(String username, String password, String role) {
        try {
            AdminEntity admin = new AdminEntity();
            admin.setUsername(username);
            admin.setPassword(password);
            admin.setRole(role);
            ar.save(admin);
            return admin;
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
            return null;
        }
    }

    public AdminEntity updateAdmin(int id, String username, String password, String role) {
        try {
            AdminEntity admin = this.getAdminById(id);
            admin.setUsername(username);
            admin.setPassword(password);
            admin.setRole(role);
            ar.save(admin);
            return admin;
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
            return null;
        }
    }

    public void deleteAdminById(int id) {
        try {
            ar.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
    }
}
