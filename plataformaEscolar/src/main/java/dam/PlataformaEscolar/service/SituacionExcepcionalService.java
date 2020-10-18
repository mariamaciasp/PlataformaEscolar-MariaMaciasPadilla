package dam.PlataformaEscolar.service;

import dam.PlataformaEscolar.modelo.*;
import dam.PlataformaEscolar.repository.SituacionExcepcionalRepository;
import dam.PlataformaEscolar.service.baseService.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SituacionExcepcionalService extends BaseService <SituacionExcepcional,
        SituacionExcepcionalPK, SituacionExcepcionalRepository>{


    private final AlumnoServicio alumnoServicio;

    public SituacionExcepcionalService(SituacionExcepcionalRepository repo, AlumnoServicio alumnoServicio) {
        super(repo);
        this.alumnoServicio = alumnoServicio;
    }

    // Método que matricula a un alumno en todas las asignaturas de un curso
    /*public Alumno matricularAlumno(Alumno alumno, Curso curso) {
        curso.addAlumno(alumno);
        alumnoServicio.save(alumno);
        for (Asignatura asignatura : curso.getAsignaturas()) {
            SituacionExcepcional m = new SituacionExcepcional(alumno, asignatura);
            this.save(m);
        }

        // Ejecutamos la consulta para "refrescar" el alumno
        // con sus asociaciones, de manera que las colecciones
        // de tipo EAGER vendrán rellenas de datos sin
        // necesidad de métodos helper.
        return alumnoServicio.findById(alumno.getId());

    }*/


}
