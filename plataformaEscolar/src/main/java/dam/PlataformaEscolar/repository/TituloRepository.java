package dam.PlataformaEscolar.repository;

import dam.PlataformaEscolar.modelo.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TituloRepository extends JpaRepository <Titulo, Long> {

    @Query("select distinct t from titulo t join fetch t.cursos")
    List<Titulo> findAllJoin();

}
