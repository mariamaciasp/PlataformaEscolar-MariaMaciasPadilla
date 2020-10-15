package dam.PlataformaEscolar.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Titulo {

    private String nombre;
    // se podría poner también asociación con el tutor del curso, con profesor, pero ya si eso
}
