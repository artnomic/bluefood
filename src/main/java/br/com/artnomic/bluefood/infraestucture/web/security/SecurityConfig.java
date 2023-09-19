package br.com.artnomic.bluefood.infraestucture.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {


//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/images/**", "/css/**", "/js/**", "/public/**", "/sbpay/**").permitAll()
//                .antMatchers("/cliente/**").hasRole(Role.CUSTOMER.toString())
//                .antMatchers("/restaurante/**").hasRole(Role.RESTAURANT.toString())
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/login-error")
//                .successHandler(authenticationSuccessHandler())
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .permitAll();
//    }
}
