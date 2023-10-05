package br.com.artnomic.bluefood.infraestucture.web.security;

import br.com.artnomic.bluefood.util.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Role role = SecurityUtils.loggedUser().getRole();
        if (role == Role.CUSTOMER) {
            response.sendRedirect("customer/home");
        } else if (role == Role.RESTAURANT) {
            response.sendRedirect("restaurant/home");
        } else {
            throw new IllegalStateException("Erro de Autenticação");
        }
    }
}
