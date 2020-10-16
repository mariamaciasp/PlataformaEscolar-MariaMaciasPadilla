package dam.PlataformaEscolar.service;

import dam.PlataformaEscolar.modelo.Curso;
import dam.PlataformaEscolar.repository.CursoRepository;
import dam.PlataformaEscolar.service.baseService.BaseService;
import org.springframework.stereotype.Service;

@Service
public class CursoServicio extends BaseService <Curso, Long, CursoRepository> {

    public CursoServicio (CursoRepository repo) {
        super(repo);

    }

}
