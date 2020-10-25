package dam.PlataformaEscolar.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class SolicitudAmpliacionMatricula {

    //@Id
    //@GeneratedValue
    //private long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaSolicitud;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaResolucion;

    private String estado;


    @EmbeddedId
    private SolicitudApliacionMatriculaPK id = new SolicitudApliacionMatriculaPK();


    @ManyToOne
    @MapsId("alumno_id")
    @JoinColumn(name="alumno_id")
    private Alumno alumno;


    @ManyToOne
    @MapsId("asignatura_id")
    @JoinColumn(name="asignatura_id")
    private Asignatura asignatura;


    public SolicitudAmpliacionMatricula(LocalDate fechaSolicitud, LocalDate fechaResolucion, String estado) {
        this.fechaSolicitud = fechaSolicitud;
        this.fechaResolucion = fechaResolucion;
        this.estado = estado;
    }

    public SolicitudAmpliacionMatricula(LocalDate fechaSolicitud, LocalDate fechaResolucion, String estado,
                                        Asignatura asignatura) {
        this.fechaSolicitud = fechaSolicitud;
        this.fechaResolucion = fechaResolucion;
        this.estado = estado;
        this.asignatura = asignatura;
    }

    public SolicitudAmpliacionMatricula(LocalDate fechaSolicitud, LocalDate fechaResolucion, String estado,
                                        Alumno alumno, Asignatura asignatura) {
        this.fechaSolicitud = fechaSolicitud;
        this.fechaResolucion = fechaResolucion;
        this.estado = estado;
        this.alumno = alumno;
        this.asignatura = asignatura;
    }

    public SolicitudAmpliacionMatricula(LocalDate fechaSolicitud, String estado, Asignatura asignatura) {
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
        this.asignatura = asignatura;
    }
}
