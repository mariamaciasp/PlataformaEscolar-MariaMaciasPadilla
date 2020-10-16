package dam.PlataformaEscolar.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class SolicitudAmpliacionMatricula {

    @Id
    @GeneratedValue
    private long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaSolicitud;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaResolucion;

    private String estado;
}
