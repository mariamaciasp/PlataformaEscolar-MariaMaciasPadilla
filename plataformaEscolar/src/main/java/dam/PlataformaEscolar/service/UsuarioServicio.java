package dam.PlataformaEscolar.service;

import dam.PlataformaEscolar.modelo.Usuario;
import dam.PlataformaEscolar.repository.UsuarioRepository;
import dam.PlataformaEscolar.service.baseService.BaseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServicio extends BaseService<Usuario, Long, UsuarioRepository> {

    public UsuarioServicio (UsuarioRepository repo) {
        super(repo);
    }

    public Optional<Usuario> buscarPorEmail (String email) {
        return repositorio.findFirstByEmail(email);
    }

}
