package dam.PlataformaEscolar.service;

import dam.PlataformaEscolar.modelo.Asignatura;
import dam.PlataformaEscolar.modelo.Curso;
import dam.PlataformaEscolar.modelo.Horario;
import dam.PlataformaEscolar.repository.HorarioRepository;
import dam.PlataformaEscolar.service.baseService.BaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HorarioService extends BaseService<Horario, Long, HorarioRepository> {


    public HorarioService(HorarioRepository repo) {
        super(repo);

    }

    public List<Horario> ordenarLista (List <Horario> listaHorario) {
        listaHorario = listaHorario.stream().
                sorted(Comparator.comparingInt(Horario::getDia)).collect(Collectors.toList());
        return listaHorario;
    }

    public List<Horario> ordenarHora (List <Horario> listaHorario, int hora) {
        List<Horario> lista = new ArrayList<>();
        for (Horario horario : listaHorario) {
            if (horario.getHora() == hora) {
                lista.add(horario);
            }
        }
        return lista;
    }

    public List<List<Horario>> ordenarHorario (List<Horario> listaHorario) {
        List<List<Horario>> lista = new ArrayList<>();
        for (int i=1; i<7; i++){
            lista.add(this.ordenarLista(this.ordenarHora(listaHorario,i)));
        }
        return lista;
    }

    public List<Horario> obtenerHorario (Curso curso) {
        List <Horario> listaHorario = new ArrayList<>();
        for (int i = 0; i < curso.getAsignaturas().size(); i++) {
            Asignatura asignatura = curso.getAsignaturas().get(i);
            for (int j = 0; j < asignatura.getHorarios().size(); j++) {
                listaHorario.add(asignatura.getHorarios().get(j));
            }
        }
        return listaHorario;
    }

}
