package dam.PlataformaEscolar.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class SituacionExcepcional implements Serializable {// hacen falta dos id???????

   // esto es la clase asociación primero que los atributos
    @EmbeddedId
    private SituacionExcepcionalPK id = new SituacionExcepcionalPK();

    @ManyToOne
    @MapsId("alumno_id")
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    @ManyToOne
    @MapsId("asignatura_id")
    @JoinColumn(name = "asignatura_id")
    private Asignatura asignatura;

     // private static final long serialVersionUID = 8682909319466153524L; //¿esto que es???
     //@Id @GeneratedValue
     //private long id;

     @DateTimeFormat(pattern = "dd/MM/yyyy")
     private LocalDate fechaSolicitud;

     private String tipo;
     private String adjunto;

     @DateTimeFormat(pattern = "dd/MM/yyyy")
     private LocalDate fechaResolucion;

     private String estado;

     public SituacionExcepcional(Alumno alumno, Asignatura asignatura, LocalDate fechaSolicitud,
                                 String tipo, String adjunto, LocalDate fechaResolucion, String estado) {
         this.alumno = alumno;
         this.asignatura = asignatura;
         this.fechaSolicitud = fechaSolicitud;
         this.tipo = tipo;
         this.adjunto = adjunto;
         this.fechaResolucion = fechaResolucion;
         this.estado = estado;

     }

     public SituacionExcepcional(LocalDate fechaSolicitud, String tipo, String adjunto,
                                 LocalDate fechaResolucion, String estado) {
         this.fechaSolicitud = fechaSolicitud;
         this.tipo = tipo;
         this.adjunto = adjunto;
         this.fechaResolucion = fechaResolucion;
         this.estado = estado;

     }
}
