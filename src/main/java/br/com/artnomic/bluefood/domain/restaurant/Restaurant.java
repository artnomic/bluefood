package br.com.artnomic.bluefood.domain.restaurant;

import br.com.artnomic.bluefood.domain.user.User;
import br.com.artnomic.bluefood.infraestucture.web.validator.UploadConstraint;
import br.com.artnomic.bluefood.util.FileType;
import javax.persistence.*;
import javax.validation.constraints.*;

import br.com.artnomic.bluefood.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@Table(name = "restaurant")
public class Restaurant extends User {

    @NotBlank(message = "O CNPJ não pode ser vazio")
    @Pattern(regexp = "[0-9]{14}", message = "O CNPJ possui formato inválido")
    @Column(length = 14, nullable = false)
    private String cnpj;

    @Size(max = 80)
    private String logotype;

    @UploadConstraint(acceptedTypes = { FileType.PNG, FileType.JPG }, message = "O arquivo de imagem não é um arquivo válido!")
    private transient MultipartFile logotypeFile;

    @NotNull(message = "A taxa de entrega não pode ser vazia")
    @Min(0)
    @Max(99)
    private BigDecimal taxDelivery;

    @NotNull(message = "O tempo de entrega não pode ser vazio")
    @Min(0)
    @Max(120)
    private Integer timeDeliveryDefault;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "restaurant_has_category",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "category_restaurant_id")
    )
    @Size(min = 1, message = "O Restaurante precisa de uma categoria")
    @ToString.Exclude
    private Set<RestaurantCategory> categories = new HashSet<>(0);

    @OneToMany(mappedBy = "restaurant")
    private Set<MenuItem>  menuItems = new HashSet<>(0);

    public void setLogotypeFileName() {
        if(getId() == null) {
            throw new IllegalStateException("É preciso primeiro gravar o registro");
        }

        this.logotype = String.format("%04d-logo.%s", getId(), FileType.of(logotypeFile.getContentType()).getExtension());
    }

    public String getCategoryAsText() {
        Set<String> strings = new LinkedHashSet<>();
        for (RestaurantCategory category:categories) {
            strings.add(category.getName());
        }

        return StringUtils.concatenate(strings);
    }
}
