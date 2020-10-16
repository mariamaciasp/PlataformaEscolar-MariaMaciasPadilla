package dam.PlataformaEscolar.service;

import dam.PlataformaEscolar.modelo.Profesor;
import dam.PlataformaEscolar.repository.ProfesorRepository;
import dam.PlataformaEscolar.service.baseService.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ProfesorServicio extends BaseService <Profesor, Long, ProfesorRepository> {

    public ProfesorServicio (ProfesorRepository repo) {
        super(repo);

    }


}
