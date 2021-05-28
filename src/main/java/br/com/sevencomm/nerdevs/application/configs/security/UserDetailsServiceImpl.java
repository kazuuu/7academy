package br.com.sevencomm.nerdevs.application.configs.security;

import br.com.sevencomm.nerdevs.data.repositories.UserRepository;
import br.com.sevencomm.nerdevs.domain.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("user não encontrado");
        }
        return user;
        }

}