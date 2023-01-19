package com.example.BankManagement.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.BankManagement.business.entity.Client;
import com.example.BankManagement.business.repository.IClientRepository;

@Service
public class UserSecurityService implements UserDetailsService{

    @Autowired
    private IClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientLogin, clientPassword = null;
        List<GrantedAuthority> authorities = null;

        Optional<Client> client = clientRepository.findByLogin(username);

        if(!client.isPresent()){
            throw new UsernameNotFoundException(username);
        }

        clientLogin = client.get().getLogin();
        clientPassword = client.get().getPassword();
        authorities = new ArrayList<>();

        return new User(clientLogin, clientPassword, authorities);
    }
    
}
