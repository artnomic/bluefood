package br.com.artnomic.bluefood.infraestucture.web.controller;

import br.com.artnomic.bluefood.application.service.CustomerService;
import br.com.artnomic.bluefood.application.service.RestaurantService;
import br.com.artnomic.bluefood.application.service.exception.ValidationException;
import br.com.artnomic.bluefood.domain.customer.Customer;
import br.com.artnomic.bluefood.domain.customer.CustomerRepository;
import br.com.artnomic.bluefood.domain.restaurant.Restaurant;
import br.com.artnomic.bluefood.domain.restaurant.RestaurantCategory;
import br.com.artnomic.bluefood.domain.restaurant.RestaurantCategoryRepository;
import br.com.artnomic.bluefood.domain.restaurant.SearchFilter;
import br.com.artnomic.bluefood.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestaurantCategoryRepository restaurantCategoryRepository;

    @Autowired
    private CustomerService
            customerService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(path = "/home")
    public String home(Model model) {
        List<RestaurantCategory> categories = restaurantCategoryRepository.findAll(Sort.by("name"));
        model.addAttribute("categories", categories);
        model.addAttribute("searchFilter", new SearchFilter());

        return "customer/customer-home";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        Integer customerId = SecurityUtils.loggedCustomer().getId();

        Customer customer = customerRepository.findById(customerId).orElseThrow();
        model.addAttribute("customer", customer);
        ControllerHelper.setEditMode(model, true);

        return "customer/customer-registration";
    }

    @PostMapping("/save")
    public String save(
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

        ControllerHelper.setEditMode(model, true);
        return "customer/customer-registration";
    }

    @GetMapping(path = "/search")
    public String search(@ModelAttribute("searchFilter") SearchFilter filter,
                         Model model) {

        List<Restaurant> restaurants = restaurantService.search(filter);
        model.addAttribute("restaurants", restaurants);

        ControllerHelper.addCategoriesToRequest(restaurantCategoryRepository, model);
        model.addAttribute("searchFilter", new SearchFilter());

        return "customer/customer-search";
    }
}
