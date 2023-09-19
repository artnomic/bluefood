package br.com.artnomic.bluefood.application.service;

import br.com.artnomic.bluefood.application.service.exception.ValidationException;
import br.com.artnomic.bluefood.domain.customer.Customer;
import br.com.artnomic.bluefood.domain.customer.CustomerRepository;
import br.com.artnomic.bluefood.domain.restaurant.Restaurant;
import br.com.artnomic.bluefood.domain.restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ImageService imageService;

    @Transactional
    public void saveRestaurant(Restaurant restaurant) throws ValidationException {

        if(!validateEmail(restaurant.getEmail(), restaurant.getId())) {
            throw new ValidationException("O e-mail já cadastrado.");
        }

        if (restaurant.getId() != null) {
            Restaurant restaurantDB = restaurantRepository.findById(restaurant.getId()).orElseThrow();
            restaurant.setPassword(restaurantDB.getPassword());
        } else {
            restaurant.encryptPassword();
            restaurant = restaurantRepository.save(restaurant);
            restaurant.setLogotypeFileName();
            imageService.uploadLogotype(restaurant.getLogotypeFile(), restaurant.getLogotype());
        }

        restaurantRepository.save(restaurant);
    }

    private boolean validateEmail(String email, Integer id) {
        Customer customer = customerRepository.findByEmail(email);
        Restaurant restaurant = restaurantRepository.findByEmail(email);

        if(customer != null) return false;

        if(restaurant != null) {
            if (id == null) {
                return false;
            }
            if(!restaurant.getId().equals(id)) {
                return false;
            }
        }

        return true;
    }
}
