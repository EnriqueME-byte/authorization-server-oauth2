package com.sakicorp.service;

import com.sakicorp.entities.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;

    public UserDetailsImpl(Usuario usuario){
        this.usuario = usuario;
        System.out.println("constructor");
        System.out.println(usuario.toString());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(usuario.getEnabled().equals('1'))
            return true;
        return false;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getNombre() {
        return usuario.getNombre();
    }

    public String getApellido() {
        return usuario.getApellido();
    }
}