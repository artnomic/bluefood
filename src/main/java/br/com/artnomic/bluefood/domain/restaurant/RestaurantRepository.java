package br.com.artnomic.bluefood.domain.restaurant;

import br.com.artnomic.bluefood.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    public Restaurant findByEmail (String email);

    public List<Restaurant> findByNameIgnoreCaseContaining(String name);

    public List<Restaurant> findByCategories_Id(Integer categoryId);
}
