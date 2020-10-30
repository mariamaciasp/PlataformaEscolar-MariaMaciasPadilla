package dam.PlataformaEscolar.controladores;

import dam.PlataformaEscolar.modelo.Profesor;
import dam.PlataformaEscolar.service.*;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profesor")
public class ControllerProfesor {

    @Autowired
    private ProfesorServicio servicioProfesor;
    @Autowired
    private TituloService servicioTitulo;
    @Autowired
    private CursoServicio servicioCurso;
    @Autowired
    private AsignaturaServicio servicioAsignatura;
    @Autowired
    private HorarioService servicioHorario;
    @Autowired
    private AlumnoServicio servicioAlumno;


    @GetMapping({"/","inicio"})
    public String inicioProfesor() {
        return "/profesor/inicioProfesor";
    }

    // TÍTULO

    @GetMapping("/titulos")
    public String titulos (@AuthenticationPrincipal Profesor profesor, Model model) {
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        model.addAttribute("listaTitulos", servicioTitulo.findAll());
        return "/profesor/titulosProfesor";
    }

    @GetMapping("titulos/mostrarCursos/{id}")
    public String titulosCursos (@AuthenticationPrincipal Profesor profesor, @PathVariable long id,
                                 Model model) {
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        model.addAttribute("listaCursos", servicioTitulo.findById(id).getCursos());
        return "/profesor/cursosProfesor";
    }

    // CURSO

    @GetMapping("/cursos")
    public String cursos (@AuthenticationPrincipal Profesor profesor, Model model) {
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        model.addAttribute("listaCursos", servicioCurso.findAll());
        return "profesor/cursosProfesor";
    }

    @GetMapping("/cursos/horario/{id}")
    public String cursoHorario (@AuthenticationPrincipal Profesor profesor, @PathVariable("id") long id,
                                Model model){
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        model.addAttribute("horarios",
                servicioHorario.ordenarHorario(servicioHorario.obtenerHorario(servicioCurso.findById(id))));
        return "profesor/horarioProfesor";
    }

    @GetMapping("/cursos/mostrarAsignaturas/{id}")
    public String mostrarAsignaturasCurso (@AuthenticationPrincipal Profesor profesor, @PathVariable long id,
                                           Model model) {
        model.addAttribute("listaAsignaturas", servicioCurso.findById(id).getAsignaturas());
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        return "/profesor/asignaturasProfesor";
    }

    @GetMapping("/cursos/mostrarAlumnos/{id}")
    public String mostrarAlumnosCurso (@AuthenticationPrincipal Profesor profesor, @PathVariable long id,
                                       Model model) {
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        model.addAttribute("listaAlumnos", servicioCurso.findById(id).getAlumnos());

        return "/profesor/alumnosProfesor";

    }



    //

    @GetMapping("/asignaturas")
    public String asignaturas (@AuthenticationPrincipal Profesor profesor, Model model) {
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        model.addAttribute("listaAsignaturas", servicioAsignatura.findAll());
        return "profesor/asignaturasProfesor";
    }

    @GetMapping("/alumnos")
    public String alumnos (@AuthenticationPrincipal Profesor profesor, Model model) {
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        model.addAttribute("listaAlumnos", servicioAlumno.findAll());
        return "profesor/alumnosProfesor";
    }
    //@GetMapping("/horario")
    //public String horario (@AuthenticationPrincipal Profesor profesor, Model model)
}
