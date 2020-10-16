package dam.PlataformaEscolar.repository;

import dam.PlataformaEscolar.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
