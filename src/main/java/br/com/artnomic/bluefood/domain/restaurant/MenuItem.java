package br.com.artnomic.bluefood.domain.restaurant;

import br.com.artnomic.bluefood.infraestucture.web.validator.UploadConstraint;
import br.com.artnomic.bluefood.util.FileType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "menu_item")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MenuItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O nome não pode ser vazio")
    @Size(max = 50)
    private String name;

    @NotBlank(message = "A categoria não pode ser vazio")
    @Size(max = 25)
    private String category;

    @NotBlank(message = "A descrição não pode ser vazio")
    @Size(max = 150)
    private String description;

    @Size(max = 50)
    private String image;

    @NotNull(message = "O preço não pode ser vazio")
    @Min(0)
    private BigDecimal price;

    @NotNull
    private Boolean emphasis;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @UploadConstraint(acceptedTypes = { FileType.PNG, FileType.JPG }, message = "O arquivo não é um arquivo de imagem válido")
    private transient MultipartFile imageFile;

    public void setImageFileName() {
        if (getId() == null) {
            throw new IllegalStateException("O Objeto precisa ser criado primeiro.");
        }

        this.image = String.format("%04d-comida.%s", getId(), FileType.of(imageFile.getContentType()).getExtension());
    }
}
