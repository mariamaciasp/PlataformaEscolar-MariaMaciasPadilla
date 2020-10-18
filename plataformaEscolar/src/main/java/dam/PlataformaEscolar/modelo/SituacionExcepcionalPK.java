package dam.PlataformaEscolar.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor @NoArgsConstructor
@Embeddable
public class SituacionExcepcionalPK implements Serializable {

    private static final long serialVersionUID = 8682909319466153524L;

    long alumno_id;

    long asignatura_id;

}
