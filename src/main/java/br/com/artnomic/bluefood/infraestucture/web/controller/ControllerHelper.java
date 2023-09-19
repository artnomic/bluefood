package br.com.artnomic.bluefood.infraestucture.web.controller;

import br.com.artnomic.bluefood.domain.restaurant.RestaurantCategory;
import br.com.artnomic.bluefood.domain.restaurant.RestaurantCategoryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import java.util.List;

public class ControllerHelper {

    public static  void setEditMode(Model model, boolean isEdit) {
        model.addAttribute("editMode", isEdit);
    }

    public static void addCategoriesToRequest(RestaurantCategoryRepository repository, Model model) {
        List<RestaurantCategory> categories = repository.findAll(Sort.by("name"));
        model.addAttribute("categories", categories);
    }
}
