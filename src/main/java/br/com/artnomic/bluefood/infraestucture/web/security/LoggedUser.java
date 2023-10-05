package br.com.artnomic.bluefood.infraestucture.web.security;

import br.com.artnomic.bluefood.domain.customer.Customer;
import br.com.artnomic.bluefood.domain.restaurant.Restaurant;
import br.com.artnomic.bluefood.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class LoggedUser implements UserDetails {

    private User user;
    private Role role;
    private Collection<? extends GrantedAuthority> roles;

    public LoggedUser(User user) {
        this.user = user;

        Role role;
        if(user instanceof Customer) {
            role = Role.CUSTOMER;
        } else if(user instanceof Restaurant) {
            role = Role.RESTAURANT;
        } else {
            throw new IllegalStateException("O tipo de usuário não é válido");
        }

        this.role = role;
        this.roles = List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
        return true;
    }

    public Role getRole() {
        return role;
    }

    public User getUser() {
        return user;
    }
}
