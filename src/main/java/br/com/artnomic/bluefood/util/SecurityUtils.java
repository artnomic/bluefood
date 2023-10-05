package br.com.artnomic.bluefood.util;

import br.com.artnomic.bluefood.domain.customer.Customer;
import br.com.artnomic.bluefood.domain.restaurant.Restaurant;
import br.com.artnomic.bluefood.infraestucture.web.security.LoggedUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static LoggedUser loggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }

        return (LoggedUser) authentication.getPrincipal();
    }

    public static Customer loggedCustomer() {
        LoggedUser loggedUser = loggedUser();

        if(loggedUser == null) {
            throw new IllegalStateException("Não existe um usuário logado!");
        }

        if (!(loggedUser.getUser() instanceof Customer)) {
            throw new IllegalStateException("O usuário logado não é um cliente");
        }

        return (Customer) loggedUser.getUser();
    }

    public static Restaurant loggedRestaurant() {
        LoggedUser loggedUser = loggedUser();

        if(loggedUser == null) {
            throw new IllegalStateException("Não existe um usuário logado!");
        }

        if (!(loggedUser.getUser() instanceof Restaurant)) {
            throw new IllegalStateException("O usuário logado não é um restaurante");
        }

        return (Restaurant) loggedUser.getUser();
    }
}
