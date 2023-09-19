package br.com.artnomic.bluefood.domain.user;

import br.com.artnomic.bluefood.util.StringUtils;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public class User implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O nome não pode ser vazio")
    @Size(max = 80, message = "O nome é muito grande")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "O e-mail não pode ser vazio")
    @Size(max = 60, message = "O e-mail é muito grande")
    @Email(message = "O e-mail é inválido")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "A senha são pode ser vazio")
    @Size(max = 80, message = "A senha é muito grande")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "O telefone/celular não pode ser vazio")
    @Pattern(regexp = "[0-9]{10,11}", message = "O telefone/celular possui formato inválido")
    @Column(length = 11, nullable = false)
    private String phone;

    public void encryptPassword() {
         this.password = StringUtils.encrypt(this.password);
    }
}
