package com.apirest.controller;

import com.apirest.dto.JwtDTO;
import com.apirest.dto.LoginDTO;
import com.apirest.dto.UserDTO;
import com.apirest.security.JwtTokenProvider;
import com.apirest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthControlller {
    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO ){
        UserDTO newUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> loginUser(@RequestBody LoginDTO login){
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(),login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtTokenProvider.generateToken(auth);

        UserDetails userDetails = (UserDetails)auth.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt,userDetails.getUsername(),userDetails.getAuthorities());
        return ResponseEntity.status(HttpStatus.OK).body(jwtDTO);
    }

}
