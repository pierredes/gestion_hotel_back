package com.example.hotel.securite;

import com.example.hotel.entities.AdminEntity;
import com.example.hotel.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminRepository ar;

    @Override
    public AdminDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminEntity admin = ar.findByUsername(username);
        if (admin == null) {
            throw new UsernameNotFoundException("No user named " + username);
        } else {
            System.out.println(admin);
            return new AdminDetailsImpl(admin);
        }
    }
}
