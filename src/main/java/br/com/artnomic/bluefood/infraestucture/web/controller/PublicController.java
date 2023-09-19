package br.com.artnomic.bluefood.infraestucture.web.controller;

import br.com.artnomic.bluefood.application.service.CustomerService;
import br.com.artnomic.bluefood.application.service.RestaurantService;
import br.com.artnomic.bluefood.application.service.exception.ValidationException;
import br.com.artnomic.bluefood.domain.customer.Customer;
import br.com.artnomic.bluefood.domain.restaurant.Restaurant;
import br.com.artnomic.bluefood.domain.restaurant.RestaurantCategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/public")
public class PublicController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantCategoryRepository restaurantCategoryRepository;

    @GetMapping("/customer/new")
    public String newCustomer(Model model) {
        model.addAttribute("customer" , new Customer());
        ControllerHelper.setEditMode(model, false);
        return "customer/customer-registration";
    }

    @GetMapping("/restaurant/new")
    public String newRestaurant(Model model) {
        model.addAttribute("restaurant" , new Restaurant());
        ControllerHelper.setEditMode(model, false);
        ControllerHelper.addCategoriesToRequest(restaurantCategoryRepository, model);
        return "restaurant/restaurant-registration";
    }

    @PostMapping(path = "/customer/save")
    public String saveCustomer(
            @ModelAttribute("customer") @Valid Customer customer,
            Errors errors,
            Model model) {

        if(!errors.hasErrors()) {
            try {
                customerService.saveCustomer(customer);
                model.addAttribute("msg", "Cliente registrado com sucesso!");
            } catch (ValidationException e) {
                errors.rejectValue("email", null, e.getMessage());
            }
        }

        ControllerHelper.setEditMode(model, false);
        return "customer/customer-registration";
    }

    @PostMapping(path = "/restaurant/save")
    public String saveRestaurant(
            @ModelAttribute("restaurant") @Valid Restaurant restaurant,
            Errors errors,
            Model model) {

        if(!errors.hasErrors()) {
            try {
                restaurantService.saveRestaurant(restaurant);
                model.addAttribute("msg", "Restaurante registrado com sucesso!");
            } catch (ValidationException e) {
                errors.rejectValue("email", null, e.getMessage());
            }
        }

        ControllerHelper.setEditMode(model, false);
        ControllerHelper.addCategoriesToRequest(restaurantCategoryRepository, model);
        return "restaurant/restaurant-registration";
    }
}
