package dam.PlataformaEscolar.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Entity
@Data @Builder
@AllArgsConstructor @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario{ // implements UserDetails y poner la herencia bien

    @Id @GeneratedValue
    private long id;

    private String nombre;
    private String apellidos;

    @Column(unique=true)
    private String email;


}
