package dam.PlataformaEscolar.modelo;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Profesor extends Usuario{

    private boolean esJefeDeEstudios;

    @Override //¿Cómo especifico que ele jefe de estudios es admin?
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ¿esto está bien?
        if (esJefeDeEstudios==true) {
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

}
