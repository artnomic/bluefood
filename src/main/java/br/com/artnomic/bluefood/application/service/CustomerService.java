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
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Transactional
    public void saveCustomer(Customer customer) throws ValidationException {

        if(!validateEmail(customer.getEmail(), customer.getId())) {
            throw new ValidationException("O e-mail já cadastrado.");
        }

        if (customer.getId() != null) {
            Customer customerDB = customerRepository.findById(customer.getId()).orElseThrow();
            customer.setPassword(customerDB.getPassword());
        } else {
            customer.encryptPassword();
        }

        customerRepository.save(customer);
    }

    private boolean validateEmail(String email, Integer id) {
        Restaurant restaurant = restaurantRepository.findByEmail(email);

        if (restaurant != null) return false;

        Customer customer = customerRepository.findByEmail(email);

        if(customer != null) {
            if (id == null) {
                return false;
            }
            if(!customer.getId().equals(id)) {
                return false;
            }
        }

        return true;
    }
}
