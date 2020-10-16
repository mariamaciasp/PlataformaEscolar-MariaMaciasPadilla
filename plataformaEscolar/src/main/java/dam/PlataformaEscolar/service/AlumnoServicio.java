package dam.PlataformaEscolar.service;

import dam.PlataformaEscolar.modelo.Alumno;
import dam.PlataformaEscolar.repository.AlumnoRepository;
import dam.PlataformaEscolar.service.baseService.BaseService;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServicio extends BaseService<Alumno, Long, AlumnoRepository> {

    public AlumnoServicio (AlumnoRepository repo) {
        super(repo);
    }

}
