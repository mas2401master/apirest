package com.apirest.security;

import com.apirest.entity.Rol;
import com.apirest.entity.UserApp;
import com.apirest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp userApp;
        if(!userRepository.existsByUsername(username)){
            throw new UsernameNotFoundException("username does not exist: "+username);
        }
        userApp = userRepository.findByUsername(username).get();
        return new User(userApp.getUsername(),userApp.getPassword(),mapRols(userApp.getRols()));
    }

    private Collection<? extends GrantedAuthority> mapRols(Set<Rol> rols){
        return rols.stream().map(rol ->
                new SimpleGrantedAuthority(rol.getName().toString())
            ).collect(Collectors.toList()
        );
    }

}
