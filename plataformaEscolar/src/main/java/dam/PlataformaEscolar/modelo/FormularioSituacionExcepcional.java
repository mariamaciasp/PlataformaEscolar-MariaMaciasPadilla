package dam.PlataformaEscolar.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Builder @Data
@NoArgsConstructor @AllArgsConstructor
public class FormularioSituacionExcepcional {

    private long idAsignatura;

}
