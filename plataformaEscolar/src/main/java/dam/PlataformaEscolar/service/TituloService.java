package dam.PlataformaEscolar.service;

import dam.PlataformaEscolar.modelo.Titulo;
import dam.PlataformaEscolar.repository.TituloRepository;
import dam.PlataformaEscolar.service.baseService.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TituloService extends BaseService <Titulo, Long, TituloRepository> {

    public TituloService (TituloRepository repo) {
        super(repo);
    }

    @Override
    public List<Titulo> findAll() {

        //this
        return this.repositorio.findAllJoin();
    }


}
