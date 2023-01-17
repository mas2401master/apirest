package com.apirest.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtDTO {
    @Setter @Getter
    private String token;
    @Setter @Getter
    private String bearer = "Bearer";
    @Setter @Getter
    private String username;
    @Setter @Getter
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDTO(String token, String username, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.username = username;
        this.authorities = authorities;
    }

}
