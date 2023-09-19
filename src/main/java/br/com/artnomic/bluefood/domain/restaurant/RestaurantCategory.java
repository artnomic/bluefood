package br.com.artnomic.bluefood.domain.restaurant;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "restaurant_category")
public class RestaurantCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    @Size(max = 50)
    private String image;

    @ManyToMany(mappedBy = "categories")
    private Set<Restaurant> restaurants = new HashSet<>(0);
}
