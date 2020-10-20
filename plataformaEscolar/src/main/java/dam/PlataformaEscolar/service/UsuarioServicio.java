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

    public String codigoAleatorio () {
        String codigo = "";
        int numero = (int) (Math.random()*10);
        codigo = codigo+numero; // agrego el número aleatorio a mi código
        int caracteres =  (int)(Math.random()*4)+2; // genero aleatorios para los caracteres
        for (int i = 0; i<caracteres;i++){
            int codigoAscii = (int)Math.floor(Math.random()*(122 - 97)+97); // los casteo como código ascii con char
            codigo = codigo + (char)codigoAscii; // los agrego a la lista de palabras
        }
        codigo = codigo.toUpperCase();
        return codigo;

    }

}
