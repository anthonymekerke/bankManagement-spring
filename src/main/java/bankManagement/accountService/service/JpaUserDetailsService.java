package bankManagement.accountService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bankManagement.accountService.domain.Client;
import bankManagement.accountService.repository.ClientRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService{

    private ClientRepository clientRepository;

    public JpaUserDetailsService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

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
