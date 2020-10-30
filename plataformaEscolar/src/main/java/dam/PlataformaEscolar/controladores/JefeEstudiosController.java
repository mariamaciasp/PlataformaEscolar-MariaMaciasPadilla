package dam.PlataformaEscolar.controladores;

import dam.PlataformaEscolar.modelo.*;
import dam.PlataformaEscolar.service.*;
import dam.PlataformaEscolar.upload.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/jefeEstudios")
public class JefeEstudiosController {

    @Autowired
    private TituloService servicioTitulo;
    @Autowired
    private CursoServicio servicioCurso;
    @Autowired
    private AlumnoServicio servicioAlumno;
    @Autowired
    private ProfesorServicio servicioProfesor;
    @Autowired
    private EnvioEmail envioEmail;
    @Autowired
    private UsuarioServicio servicioUsuario;
    @Autowired
    private AsignaturaServicio servicioAsignatura;
    @Autowired
    private HorarioService servicioHorario;
    @Autowired
    private SituacionExcepcionalService servicioExcepcional;
    @Autowired
    private FileSystemStorageService fileSystemStorageService;


    @GetMapping("/")
    public String inicioJefeEstudios (@AuthenticationPrincipal Profesor profesor, Model model) {
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        return "jefeEstudios/inicioJE";
    }

    // ALUMNOS

    @GetMapping("/alumnos")
    public String alumnos(@AuthenticationPrincipal Profesor profesor, Model model){
        model.addAttribute("listaAlumnos",servicioAlumno.findAll());
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        return "jefeEstudios/alumnos";
    }

    @GetMapping("/registroAlumno")
    public String nuevoAlumnoForm (@AuthenticationPrincipal Profesor profesor, Model model) {
        model.addAttribute("alumnoForm", new Alumno());
        model.addAttribute("listaCursos", servicioCurso.findAll());
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        return "jefeEstudios/formularioAlumno";
    }

    @PostMapping("/nuevoAlumno/submit")
    public String registroAlumno (@ModelAttribute("alumnoForm") Alumno nuevoAlumno) {
        String codigo = servicioUsuario.codigoAleatorio();
        nuevoAlumno.setActivado(false);
        nuevoAlumno.setCodigoActivacion(codigo);
        servicioAlumno.save(nuevoAlumno);
        envioEmail.sendEmail(nuevoAlumno, "Alta cuenta","Su código de alta en " +
                "Triana eschool es " + codigo);
        return "redirect:/jefeEstudios/";

    }

    @GetMapping("/editAlumno/{id}")
    public String editAlumno (@AuthenticationPrincipal Profesor profesor, @PathVariable long id, Model model) {
        if (servicioAlumno.findById(id) != null) {
            model.addAttribute("alumnoForm", servicioAlumno.findById(id));
            model.addAttribute("listaCursos", servicioCurso.findAll());
            model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
            return "/jefeEstudios/formularioAlumno";
        }
        return "redirect:/jefeEstudios/";
    }

    @PostMapping("/editAlumno/submit")
    public String editAlumnoSubmit (@ModelAttribute("alumnoForm") Alumno editAlumno) {
        servicioAlumno.edit(editAlumno);
        return "redirect:/jefeEstudios";
    }

    @GetMapping("/eliminarAlumno/{id}")
    public String deleteAlumno (@AuthenticationPrincipal Profesor profesor, @PathVariable long id, Model model) {
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        if (servicioAlumno.findById(id)!=null) {
            servicioAlumno.deleteById(id);
        }
        return "redirect:/jefeEstudios/alumnos";
    }

    /*@GetMapping("/alumnos/mostrarCurso/{id}")
    public String mostrarCursoAlumno (@PathVariable long id, Model model) {
        model.addAttribute("listaAlumnos", servicioAlumno.findById(id).getCurso());
        return "jefeEstudios/cursos";
    }*/


    @GetMapping("/profesor")
    public String profesores(@AuthenticationPrincipal Profesor profesor, Model model){
        model.addAttribute("listaProfesores",servicioProfesor.findAll());
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        return "jefeEstudios/profesor";
    }

    @GetMapping("/registroProfesor")
    public String nuevoProfesorForm (@AuthenticationPrincipal Profesor profesor, Model model) {
        model.addAttribute("profesorForm", new Profesor());
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        return "jefeEstudios/formularioProfesor";
    }

    @PostMapping("/nuevoProfesor/submit")
    public String registroProfesor (@ModelAttribute("profesorForm") Profesor nuevoProfesor) {
        String codigo = servicioUsuario.codigoAleatorio();
        nuevoProfesor.setActivado(false); // la cuenta sin activar
        nuevoProfesor.setCodigoActivacion(codigo);
        servicioProfesor.save(nuevoProfesor);
        envioEmail.sendEmail(nuevoProfesor, "Alta cuenta","Su código de alta en " +
                "Triana eschool es " + codigo);

        return "redirect:/jefeEstudios/profesor";

    }

    @GetMapping("/editProfesor/{id}")
    public String editProfesor (@AuthenticationPrincipal Profesor profesor, @PathVariable long id, Model model) {
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        if (servicioProfesor.findById(id) != null) {
            model.addAttribute("profesorForm", servicioProfesor.findById(id));
            return "/jefeEstudios/formularioProfesor";
        }
        return "redirect:/jefeEstudios/profesor";
    }

    @PostMapping("/editProfesor/submit")
    public String editProfesorSubmit (@ModelAttribute("profesorForm") Profesor editProfesor) {
        servicioProfesor.edit(editProfesor);
        return "redirect:/jefeEstudios/profesor";
    }

    @GetMapping("/eliminarProfesor/{id}")
    public String deleteProfesor (@PathVariable long id, @AuthenticationPrincipal Profesor profesor, Model model) {
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        if (servicioProfesor.findById(id)!= null /*&& y por qué cuando doy a borrar elimina el de abajo!!!!?
                servicioProfesor.findById(id) != servicioProfesor.findById(profesor.getId())*/) {
            servicioProfesor.deleteById(id);
        }
        return "redirect:/jefeEstudios/profesor";
    }

    // TITULOS

    @GetMapping("/titulos")
    public String titulos (@AuthenticationPrincipal Profesor profesor, Model model) {
        //listaCategorias(); // este método sería para hacerlo sin el model.addAttribute
        model.addAttribute("listaTitulos", servicioTitulo.findAll());
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        return "jefeEstudios/titulos";
    }

    @GetMapping("/registroTitulo")
    public String nuevoTituloForm (@AuthenticationPrincipal Profesor profesor, Model model) {
        model.addAttribute("tituloForm", new Titulo());
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        return "jefeEstudios/formularioTitulo";
    }

    @PostMapping("/nuevoTitulo/submit")
    public String registroTitulo (@ModelAttribute("tituloForm") Titulo nuevoTitulo) {
        servicioTitulo.save(nuevoTitulo);
        return "redirect:/jefeEstudios/";

    }

    @GetMapping("/editTitulo/{id}")
    public String editTituloForm (@AuthenticationPrincipal Profesor profesor, @PathVariable long id, Model model) {
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        if (servicioTitulo.findById(id) != null){
            model.addAttribute("tituloForm", servicioTitulo.findById(id));
            return "jefeEstudios/formularioTitulo";
        }

        return "redirect:/jefeEstudios/";
    }

    @PostMapping("/editTitulo/submit")
    public String editTituloSubmit (@ModelAttribute("tituloForm") Titulo editTitulo){
        servicioTitulo.edit(editTitulo);
        return "redirect:/jefeEstudios/titulos";
    }

    @GetMapping("/eliminarTitulo/{id}")
    public String deleteTitulo (@AuthenticationPrincipal Profesor profesor, @PathVariable long id, Model model) {
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        if (servicioTitulo.findById(id)!=null) {
            servicioTitulo.deleteById(id);
        }

        return "redirect:/jefeEstudios/titulos";
    }

    @GetMapping("/titulos/mostrarCursos/{id}")
    public String mostrarCursosTitulos (@AuthenticationPrincipal Profesor profesor, @PathVariable long id, Model model) {
        Set<Curso> cursos = new HashSet<>(servicioTitulo.findById(id).getCursos());
        model.addAttribute("listaCursos", cursos);
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        return "/jefeEstudios/cursos";
    }


    // CURSOS


    @GetMapping("/cursos")
    public String cursos (@AuthenticationPrincipal Profesor profesor, Model model) {
        model.addAttribute("listaCursos", servicioCurso.findAll());
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        return "jefeEstudios/cursos";
    }

    @GetMapping("/registroCurso")
    public String nuevoCursoForm (@AuthenticationPrincipal Profesor profesor, Model model) {
        model.addAttribute("cursoForm", new Curso());
        model.addAttribute("listaTitulos", servicioTitulo.findAll());
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        return "jefeEstudios/formularioCurso";
    }

    @PostMapping("/nuevoCurso/submit")
    public String registroCurso (@ModelAttribute("alumnoForm") Curso nuevoCurso) {
        servicioCurso.save(nuevoCurso);
        return "redirect:/jefeEstudios/cursos";

    }

    @GetMapping("/editCurso/{id}")
    public String editCursoForm (@AuthenticationPrincipal Profesor profesor, @PathVariable long id, Model model) {
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        if (servicioCurso.findById(id) != null){
            model.addAttribute("cursoForm", servicioCurso.findById(id));
            model.addAttribute("listaTitulos", servicioTitulo.findAll());
            return "/jefeEstudios/formularioCurso";
        }
        return "redirect:/jefeEstudios/";
    }

    @PostMapping("/editCurso/submit")
    public String editCursoSubmit (@ModelAttribute("cursoForm")  Curso editCurso) {
        servicioCurso.edit(editCurso);
        return "redirect:/jefeEstudios/cursos";
    }

    @GetMapping("/eliminarCurso/{id}")
    public String deleteCurso (@AuthenticationPrincipal Profesor profesor, @PathVariable long id, Model model) {
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        if (servicioCurso.findById(id)!=null) {
            servicioCurso.deleteById(id);
        }
        return "redirect:/jefeEstudios/cursos";
    }

    @GetMapping("/cursos/horario/{id}")
    public String cursoHorario (@AuthenticationPrincipal Profesor profesor, @PathVariable("id") long id,
                                Model model){
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        model.addAttribute("horarios",
                servicioHorario.ordenarHorario(servicioHorario.obtenerHorario(servicioCurso.findById(id))));
        return "jefeEstudios/horario";
    }

    @GetMapping("/cursos/mostrarAsignaturas/{id}")
    public String mostrarAsignaturasCurso (@AuthenticationPrincipal Profesor profesor, @PathVariable long id,
                                           Model model) {
        model.addAttribute("listaAsignaturas", servicioCurso.findById(id).getAsignaturas());
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        return "/jefeEstudios/asignatura";
    }

    @GetMapping("/cursos/mostrarAlumnos/{id}")
    public String mostrarAlumnosCurso (@AuthenticationPrincipal Profesor profesor, @PathVariable long id,
                                       Model model) {
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        model.addAttribute("listaAlumnos", servicioCurso.findById(id).getAlumnos());

        return "/jefeEstudios/alumnos";

    }

    // ASIGNATURAS

    @GetMapping("/asignaturas")
    public String asignaturas (@AuthenticationPrincipal Profesor profesor, Model model) {
        model.addAttribute("listaAsignaturas", servicioAsignatura.findAll());
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        // model.addAttribute("listaHorario", servicioAsignatura.findAll());
        return "jefeEstudios/asignatura";
    }

    @GetMapping("/registroAsignatura")
    public String nuevaAsignaturaForm (@AuthenticationPrincipal Profesor profesor, Model model) {
        model.addAttribute("asignaturaForm", new Asignatura());
        model.addAttribute("listaCursos", servicioCurso.findAll());
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        return "jefeEstudios/formularioAsignatura";
    }

    @PostMapping("/nuevaAsignatura/submit")
    public String registroAsignatura (@ModelAttribute("asignaturaForm") Asignatura nuevaAsignatura) {
        servicioAsignatura.save(nuevaAsignatura);
        return "redirect:/jefeEstudios/asignaturas";
    }

    @GetMapping("/editAsignatura/{id}")
    public String editAsignaturaForm (@AuthenticationPrincipal Profesor profesor, @PathVariable long id, Model model) {
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        if (servicioAsignatura.findById(id) != null) {
            model.addAttribute("asignaturaForm", servicioAsignatura.findById(id));
            model.addAttribute("listaCursos", servicioCurso.findAll());
            return "/jefeEstudios/formularioAsignatura";
        }
        return "/jefeEstudios/cursos";
    }

    @PostMapping("/editAsignatura/submit")
    public String editAsignaturaSubmit (@ModelAttribute("asignaturaForm") Asignatura editAsignatura) {
        servicioAsignatura.edit(editAsignatura);
        return "redirect:/jefeEstudios/asignaturas";
    }

    @GetMapping("/eliminarAsignatura/{id}")
    public String deleteAsignatura (@AuthenticationPrincipal Profesor profesor, @PathVariable long id, Model model) {
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        if (servicioAsignatura.findById(id)!=null) {
            servicioAsignatura.deleteById(id);
        }
        return "redirect:/jefeEstudios/asignaturas";
    }

    // HORARIO

    @GetMapping("/registroHorario")
    public String nuevoHorarioForm (@AuthenticationPrincipal Profesor profesor, Model model) {
        model.addAttribute("horarioForm", new Horario());
        model.addAttribute("listaAsignaturas", servicioAsignatura.findAll());
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        return "jefeEstudios/formularioHorario";
    }

    @PostMapping("/nuevoHorario/submit")
    public String registroAsignatura (@ModelAttribute("horarioForm") Horario nuevoHorario) {
        servicioHorario.save(nuevoHorario);
        return "redirect:/jefeEstudios/cursos";
    }

    // Situaciones excepcionales

    @GetMapping("/situacionExcepcional")
    public String mostrarSituacionesExcepcionales (@AuthenticationPrincipal Profesor profesor, Model model) {
        model.addAttribute("listaSituacionesExcepcionales", servicioExcepcional.findAll());
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        return "jefeEstudios/situacionExcepcional";
    }

    @GetMapping("/situacionExcepcional/download/{nombre}")
    public ResponseEntity<Resource> descargarFicheros (@AuthenticationPrincipal Profesor profesor,
                                                       @PathVariable("nombre") String name, Model model){
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        Resource resource = fileSystemStorageService.loadAsResource(name);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                resource.getFilename() + "\"").body(resource);
    }

    @GetMapping("/editSituacionExcepcional/{idAlumno}/{idAsignatura}")
    public String editSituacionExcepcional (@AuthenticationPrincipal Profesor profesor,
                                            @PathVariable long idAlumno, @PathVariable long idAsignatura,
                                             Model model){
        model.addAttribute("datosProfesor", servicioProfesor.findById(profesor.getId()));
        SituacionExcepcionalPK pk = new SituacionExcepcionalPK (idAlumno,idAsignatura);
        if (servicioExcepcional.findById(pk)!=null){
            model.addAttribute("excepcionalForm", servicioExcepcional.findById(pk));
            return "jefeEstudios/formularioSituacionExcepcional";
        }
        return "redirect:/jefeEstudios/";
    }

    @PostMapping("/editSituacionExcepcional/submit")
    public String editSituacionEscepcionalSubmit (@ModelAttribute("excepcionalForm") SituacionExcepcional excepcional){
        SituacionExcepcionalPK pk = new SituacionExcepcionalPK(excepcional.getId().getAlumno_id(), excepcional.getId().getAsignatura_id());
        System.out.println(pk);
        SituacionExcepcional situacion = servicioExcepcional.findById(pk);
        situacion.setFechaResolucion(LocalDate.now());
        servicioExcepcional.edit(situacion);
        return "redirect:/jefeEstudios/situacionExcepcional";
    }


}
