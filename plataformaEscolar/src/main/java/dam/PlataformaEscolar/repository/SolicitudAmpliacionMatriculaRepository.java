package dam.PlataformaEscolar.repository;

import dam.PlataformaEscolar.modelo.SituacionExcepcionalPK;
import dam.PlataformaEscolar.modelo.SolicitudAmpliacionMatricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudAmpliacionMatriculaRepository extends
        JpaRepository<SolicitudAmpliacionMatricula,SituacionExcepcionalPK> {
}
