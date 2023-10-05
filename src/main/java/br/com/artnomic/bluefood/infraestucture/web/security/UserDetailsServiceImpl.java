package br.com.artnomic.bluefood.infraestucture.web.security;

import br.com.artnomic.bluefood.domain.customer.CustomerRepository;
import br.com.artnomic.bluefood.domain.restaurant.RestaurantRepository;
import br.com.artnomic.bluefood.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = customerRepository.findByEmail(username);

        if(user == null) {
            user = restaurantRepository.findByEmail(username);

            if(user == null) {
                throw new UsernameNotFoundException(username);
            }
        }

        return new LoggedUser(user);
    }
}
