package br.com.artnomic.bluefood.domain.restaurant;

import br.com.artnomic.bluefood.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategory, Integer> {
}
