package dam.PlataformaEscolar.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Alumno extends Usuario {

    @ManyToOne
    private Curso curso;


}
