package dam.PlataformaEscolar.repository;

import dam.PlataformaEscolar.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository <Curso, Long> {
}
