package dam.PlataformaEscolar.service;

import dam.PlataformaEscolar.modelo.Horario;
import dam.PlataformaEscolar.repository.HorarioRepository;
import dam.PlataformaEscolar.service.baseService.BaseService;
import org.springframework.stereotype.Service;

@Service
public class HorarioService extends BaseService<Horario, Long, HorarioRepository> {


    public HorarioService(HorarioRepository repo) {
        super(repo);

    }

}
