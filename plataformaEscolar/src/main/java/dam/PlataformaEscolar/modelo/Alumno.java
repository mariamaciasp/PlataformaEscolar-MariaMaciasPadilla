package dam.PlataformaEscolar.modelo;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Alumno extends Usuario {

    @ManyToOne
    private Curso curso;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ALUMNO"));
    }

    // Mantenemos esta lista, pero no añadimos helpers
    // Si queremos rellenar la lista, realizamos un JOIN FETCH
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy="alumno", fetch = FetchType.EAGER)
    private List<SituacionExcepcional> situacionesExcepcionales = new ArrayList<>();


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy="alumno", fetch = FetchType.EAGER)
    private List<SolicitudAmpliacionMatricula> solicitudAmpliacionMatriculas = new ArrayList<>();


    public Alumno(String nombre, String apellidos, String email, String password,
                  String codigoActivacion, boolean activado, Curso curso) {
        super(nombre, apellidos, email, password, codigoActivacion, activado);
        this.curso = curso;
    }

    public Alumno(String nombre, String apellidos, String email, String password,
                  String codigoActivacion, boolean activado) {
        super(nombre, apellidos, email, password, codigoActivacion, activado);

    }



}
