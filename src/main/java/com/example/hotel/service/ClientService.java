package com.example.hotel.service;

import com.example.hotel.entities.ClientEntity;
import com.example.hotel.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository cr;

    public ClientService(ClientRepository cr) {
        this.cr = cr;
    }

    public List<ClientEntity> getAllClient() {
        return (List<ClientEntity>) cr.findAll();
    }

    public ClientEntity getClientById(int id) {
        return cr.findById(id).get();
    }

    public ClientEntity addPatient(String nomComplet, String telephone, String email, String adresse) {
        try {
            ClientEntity client = new ClientEntity();
            client.setNomComplet(nomComplet);
            client.setTelephone(telephone);
            client.setEmail(email);
            client.setAdresse(adresse);
            cr.save(client);
            return client;
        } catch (Exception e) {
            System.out.println("Erreur : " + e );
            return null;
        }
    }

    public ClientEntity updadeClient(int id, String nomComplet, String telephone, String email, String adresse) {
        try {
            ClientEntity client = this.getClientById(id);
            client.setNomComplet(nomComplet);
            client.setTelephone(telephone);
            client.setEmail(email);
            client.setAdresse(adresse);
            cr.save(client);
            return client;
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
            return null;
        }
    }

    public void deleteClientById(int id) {
        try {
            cr.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
    }
}
