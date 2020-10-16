package dam.PlataformaEscolar.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class SituacionExcepcional implements Serializable {

   // private static final long serialVersionUID = 8682909319466153524L; //¿esto que es???
    @Id @GeneratedValue
    private long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaSolicitud;

    private String tipo;
    private String adjunto;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaResolucion;

    private String estado;


}
