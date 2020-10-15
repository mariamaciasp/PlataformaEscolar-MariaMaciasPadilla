package dam.PlataformaEscolar.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Profesor extends Usuario{

    private boolean esJefeDeEstudios;

}
