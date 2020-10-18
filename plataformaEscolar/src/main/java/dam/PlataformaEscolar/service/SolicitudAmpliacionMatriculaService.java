package dam.PlataformaEscolar.service;

import dam.PlataformaEscolar.modelo.SituacionExcepcionalPK;
import dam.PlataformaEscolar.modelo.SolicitudAmpliacionMatricula;
import dam.PlataformaEscolar.repository.SolicitudAmpliacionMatriculaRepository;
import dam.PlataformaEscolar.service.baseService.BaseService;

public class SolicitudAmpliacionMatriculaService extends BaseService
        <SolicitudAmpliacionMatricula, SituacionExcepcionalPK, SolicitudAmpliacionMatriculaRepository> {

    private final AlumnoServicio alumnoServicio;

    public SolicitudAmpliacionMatriculaService(SolicitudAmpliacionMatriculaRepository repo, AlumnoServicio alumnoServicio) {
        super(repo);
        this.alumnoServicio = alumnoServicio;
    }


}
