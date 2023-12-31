package br.com.artnomic.bluefood.application.test;

import br.com.artnomic.bluefood.domain.customer.Customer;
import br.com.artnomic.bluefood.domain.customer.CustomerRepository;
import br.com.artnomic.bluefood.domain.restaurant.*;
import br.com.artnomic.bluefood.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class InsertDataForTesting {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantCategoryRepository restaurantCategoryRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        customers();
        Restaurant[] restaurants = restaurants();
        menuItems(restaurants);
    }

    private Restaurant[] restaurants() {
        List<Restaurant> restaurants = new ArrayList<>();

        RestaurantCategory categoryPizza = restaurantCategoryRepository.findById(1).orElseThrow();
        RestaurantCategory categoryHamburguer = restaurantCategoryRepository.findById(2).orElseThrow();
        RestaurantCategory categorySobremesa = restaurantCategoryRepository.findById(5).orElseThrow();
        RestaurantCategory categoryJapones = restaurantCategoryRepository.findById(6).orElseThrow();

        Restaurant r = new Restaurant();
        r.setName("Bubger King");
        r.setEmail("r1@bluefood.com.br");
        r.setPassword(StringUtils.encrypt("r"));
        r.setCnpj("00000000000101");
        r.setTaxDelivery(BigDecimal.valueOf(3.2));
        r.setPhone("99876671010");
        r.getCategories().add(categoryHamburguer);
        r.getCategories().add(categorySobremesa);
        r.setLogotype("0001-logo.png");
        r.setTimeDeliveryDefault(30);
        restaurantRepository.save(r);
        restaurants.add(r);

        r = new Restaurant();
        r.setName("Mc Naldo's");
        r.setEmail("r2@bluefood.com.br");
        r.setPassword(StringUtils.encrypt("r"));
        r.setCnpj("00000000000102");
        r.setTaxDelivery(BigDecimal.valueOf(4.5));
        r.setPhone("99876671011");
        r.getCategories().add(categoryHamburguer);
        r.getCategories().add(categorySobremesa);
        r.setLogotype("0002-logo.png");
        r.setTimeDeliveryDefault(25);
        restaurantRepository.save(r);
        restaurants.add(r);

        r = new Restaurant();
        r.setName("Sbubby");
        r.setEmail("r3@bluefood.com.br");
        r.setPassword(StringUtils.encrypt("r"));
        r.setCnpj("00000000000103");
        r.setTaxDelivery(BigDecimal.valueOf(12.2));
        r.setPhone("99876671012");
        r.getCategories().add(categoryHamburguer);
        r.getCategories().add(categorySobremesa);
        r.setLogotype("0003-logo.png");
        r.setTimeDeliveryDefault(38);
        restaurantRepository.save(r);
        restaurants.add(r);

        r = new Restaurant();
        r.setName("Pizza Brut");
        r.setEmail("r4@bluefood.com.br");
        r.setPassword(StringUtils.encrypt("r"));
        r.setCnpj("00000000000104");
        r.setTaxDelivery(BigDecimal.valueOf(9.8));
        r.setPhone("99876671013");
        r.getCategories().add(categoryPizza);
        r.getCategories().add(categorySobremesa);
        r.setLogotype("0004-logo.png");
        r.setTimeDeliveryDefault(22);
        restaurantRepository.save(r);
        restaurants.add(r);

        r = new Restaurant();
        r.setName("Wiki Japa");
        r.setEmail("r5@bluefood.com.br");
        r.setPassword(StringUtils.encrypt("r"));
        r.setCnpj("00000000000105");
        r.setTaxDelivery(BigDecimal.valueOf(14.9));
        r.setPhone("99876671014");
        r.getCategories().add(categoryJapones);
        r.getCategories().add(categorySobremesa);
        r.setLogotype("0005-logo.png");
        r.setTimeDeliveryDefault(19);
        restaurantRepository.save(r);
        restaurants.add(r);

        Restaurant[] array = new Restaurant[restaurants.size()];
        return restaurants.toArray(array);
    }

    private Customer[] customers() {
        List<Customer> customers = new ArrayList<>();

        Customer c = new Customer();
        c.setName("João Silva");
        c.setEmail("joao@bluefood.com.br");
        c.setPassword(StringUtils.encrypt("c"));
        c.setCep("89300100");
        c.setCpf("03099887666");
        c.setPhone("99355430001");
        customers.add(c);
        customerRepository.save(c);

        c = new Customer();
        c.setName("Maria Torres");
        c.setEmail("maria@bluefood.com.br");
        c.setPassword(StringUtils.encrypt("c"));
        c.setCep("89300101");
        c.setCpf("03099887677");
        c.setPhone("99355430002");
        customers.add(c);
        customerRepository.save(c);

        Customer[] array = new Customer[customers.size()];
        return customers.toArray(array);
    }

    private void menuItems(Restaurant[] restaurants) {
        MenuItem mc = new MenuItem();
        mc.setCategory("Sanduíche");
        mc.setDescription("Delicioso sanduíche com molho");
        mc.setName("Double Cheese Burger Special");
        mc.setPrice(BigDecimal.valueOf(23.8));
        mc.setRestaurant(restaurants[0]);
        mc.setEmphasis(true);
        mc.setImage("0001-comida.png");
        menuItemRepository.save(mc);

        mc = new MenuItem();
        mc.setCategory("Sanduíche");
        mc.setDescription("Sanduíche padrão que mata a fome");
        mc.setName("Cheese Burger Simples");
        mc.setPrice(BigDecimal.valueOf(17.8));
        mc.setRestaurant(restaurants[0]);
        mc.setEmphasis(false);
        mc.setImage("0006-comida.png");
        menuItemRepository.save(mc);

        mc = new MenuItem();
        mc.setCategory("Sanduíche");
        mc.setDescription("Sanduíche natural com peito de peru");
        mc.setName("Sanduíche Natural da Casa");
        mc.setPrice(BigDecimal.valueOf(11.8));
        mc.setRestaurant(restaurants[0]);
        mc.setEmphasis(false);
        mc.setImage("0007-comida.png");
        menuItemRepository.save(mc);

        mc = new MenuItem();
        mc.setCategory("Bebida");
        mc.setDescription("Refrigerante com gás");
        mc.setName("Refrigerante Tradicional");
        mc.setPrice(BigDecimal.valueOf(9));
        mc.setRestaurant(restaurants[0]);
        mc.setEmphasis(false);
        mc.setImage("0004-comida.png");
        menuItemRepository.save(mc);

        mc = new MenuItem();
        mc.setCategory("Bebida");
        mc.setDescription("Suco natural e docinho");
        mc.setName("Suco de Laranja");
        mc.setPrice(BigDecimal.valueOf(9));
        mc.setRestaurant(restaurants[0]);
        mc.setEmphasis(false);
        mc.setImage("0005-comida.png");
        menuItemRepository.save(mc);

        mc = new MenuItem();
        mc.setCategory("Pizza");
        mc.setDescription("Pizza saborosa com cebola");
        mc.setName("Pizza de Calabresa");
        mc.setPrice(BigDecimal.valueOf(38.9));
        mc.setRestaurant(restaurants[3]);
        mc.setEmphasis(false);
        mc.setImage("0002-comida.png");
        menuItemRepository.save(mc);

        mc = new MenuItem();
        mc.setCategory("Japonesa");
        mc.setDescription("Delicioso Uramaki tradicional");
        mc.setName("Uramaki");
        mc.setPrice(BigDecimal.valueOf(16.8));
        mc.setRestaurant(restaurants[4]);
        mc.setEmphasis(false);
        mc.setImage("0003-comida.png");
        menuItemRepository.save(mc);
    }
}
