package dam.PlataformaEscolar.service;

import dam.PlataformaEscolar.modelo.Asignatura;
import dam.PlataformaEscolar.repository.AsignaturaRepository;
import dam.PlataformaEscolar.service.baseService.BaseService;
import org.springframework.stereotype.Service;

@Service
public class AsignaturaServicio extends BaseService <Asignatura, Long, AsignaturaRepository> {

    public AsignaturaServicio (AsignaturaRepository repo) {
        super(repo);
    }


}
