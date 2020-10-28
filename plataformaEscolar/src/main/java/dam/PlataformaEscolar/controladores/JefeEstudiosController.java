package dam.PlataformaEscolar.controladores;

import dam.PlataformaEscolar.modelo.*;
import dam.PlataformaEscolar.service.*;
import dam.PlataformaEscolar.upload.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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


    @ModelAttribute("listaTitulos")
    public List<Titulo> listaCategorias () {
        return servicioTitulo.findAll();
    }


    @GetMapping("/")
    public String inicioJefeEstudios () {
        return "jefeEstudios/inicioJE";
    }


    @GetMapping("/alumnos")
    public String alumnos(Model model){
        model.addAttribute("listaAlumnos",servicioAlumno.findAll());
        return "jefeEstudios/alumnos";
    }

    @GetMapping("/profesor")
    public String profesores(Model model){
        model.addAttribute("listaProfesores",servicioProfesor.findAll());
        return "jefeEstudios/profesor";
    }

    @GetMapping("/titulos")
    public String titulos (Model model) {
        //listaCategorias(); // este método sería para hacerlo sin el model.addAttribute
        model.addAttribute("listaTitulos", servicioTitulo.findAll());
        return "jefeEstudios/titulos";
    }

    @GetMapping("/cursos")
    public String cursos (Model model) {
        model.addAttribute("listaCursos", servicioCurso.findAll());
        return "jefeEstudios/cursos";
    }

    @GetMapping("/cursos/horario/{id}")
    public String cursoHorario (@PathVariable("id") long id, Model model){
        model.addAttribute("horarios",
                servicioHorario.ordenarHorario(servicioHorario.obtenerHorario(servicioCurso.findById(id))));
        return "jefeEstudios/horario";
    }


    @GetMapping("/asignaturas")
    public String asignaturas (Model model) {
        model.addAttribute("listaAsignaturas", servicioAsignatura.findAll());
       // model.addAttribute("listaHorario", servicioAsignatura.findAll());
        return "jefeEstudios/asignatura";
    }


    // registro alumno
    @GetMapping("/registroAlumno")
    public String nuevoAlumnoForm (Model model) {
        model.addAttribute("alumnoForm", new Alumno());
        model.addAttribute("listaCursos", servicioCurso.findAll());
        return "jefeEstudios/formularioAlumno";
    }

    @PostMapping("/nuevoAlumno/submit")
    public String registroAlumno (@ModelAttribute("alumnoForm") Alumno nuevoAlumno) {
        String codigo = servicioUsuario.codigoAleatorio();
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //nuevoAlumno.setPassword(encoder.encode(nuevoAlumno.getPassword()));
        nuevoAlumno.setActivado(false);
        nuevoAlumno.setCodigoActivacion(codigo);
        servicioAlumno.save(nuevoAlumno);
        envioEmail.sendEmail(nuevoAlumno, "Alta cuenta","Su código de alta en " +
                "Triana eschool es " + codigo);

        return "redirect:/jefeEstudios/";

    }

    // registro cursos
    @GetMapping("/registroCurso")
    public String nuevoCursoForm (Model model) {
        model.addAttribute("cursoForm", new Curso());
        model.addAttribute("listaTitulos", servicioTitulo.findAll());
        return "jefeEstudios/formularioCurso";
    }

    @PostMapping("/nuevoCurso/submit")
    public String registroCurso (@ModelAttribute("alumnoForm") Curso nuevoCurso) {
        servicioCurso.save(nuevoCurso);
        return "redirect:/jefeEstudios/";

    }

    // registro títulos
    @GetMapping("/registroTitulo")
    public String nuevoTituloForm (Model model) {
        model.addAttribute("tituloForm", new Titulo());
        return "jefeEstudios/formularioTitulo";
    }

    @PostMapping("/nuevoTitulo/submit")
    public String registroTitulo (@ModelAttribute("tituloForm") Titulo nuevoTitulo) {
        servicioTitulo.save(nuevoTitulo);
        return "redirect:/jefeEstudios/";

    }


    // registro asignaturas
    @GetMapping("/registroAsignatura")
    public String nuevaAsignaturaForm (Model model) {
        model.addAttribute("asignaturaForm", new Asignatura());
        model.addAttribute("listaCursos", servicioCurso.findAll());
        return "jefeEstudios/formularioAsignatura";
    }

    @PostMapping("/nuevaAsignatura/submit")
    public String registroAsignatura (@ModelAttribute("asignaturaForm") Asignatura nuevaAsignatura) {
        servicioAsignatura.save(nuevaAsignatura);
        return "redirect:/jefeEstudios/";
    }

    // registro horario
    @GetMapping("/registroHorario")
    public String nuevoHorarioForm (Model model) {
        model.addAttribute("horarioForm", new Horario());
        model.addAttribute("listaAsignaturas", servicioAsignatura.findAll());
        return "jefeEstudios/formularioHorario";
    }

    @PostMapping("/nuevoHorario/submit")
    public String registroAsignatura (@ModelAttribute("horarioForm") Horario nuevoHorario) {
        servicioHorario.save(nuevoHorario);
        return "redirect:/jefeEstudios/";
    }


    // registro profesores

    @GetMapping("/registroProfesor")
    public String nuevoProfesorForm (Model model) {
        model.addAttribute("profesorForm", new Profesor());
        return "jefeEstudios/formularioProfesor";
    }

    @PostMapping("/nuevoProfesor/submit")
    public String registroProfesor (@ModelAttribute("profesorForm") Profesor nuevoProfesor) {
        String codigo = servicioUsuario.codigoAleatorio();
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //nuevoProfesor.setPassword(encoder.encode(codigo));
        nuevoProfesor.setActivado(false); // la cuenta sin activar
        nuevoProfesor.setCodigoActivacion(codigo);
        servicioProfesor.save(nuevoProfesor);
        envioEmail.sendEmail(nuevoProfesor, "Alta cuenta","Su código de alta en " +
                "Triana eschool es " + codigo);

        return "redirect:/jefeEstudios/";

    }

    // calendario asignatura


    @GetMapping("/calendarioAsignatura/{id}")
    public String detallesCalendarioAsignatura (@PathVariable("id") Long id, Model model){
        if (servicioAsignatura.findById(id)!=null){
            model.addAttribute("asignatura",servicioAsignatura.findById(id));
        }
        return "/jefeEstudios/calendarioAsignatura";
    }


    @GetMapping("/calendarioAsignatura")
    public String calendarioAsignatura(@RequestParam(name="idAsignatura", required = false) Long idAsignatura,
                                       Model model){
        model.addAttribute("listaAsignaturas", servicioAsignatura.findAll());
        return "/jefeEstudios/calendarioAsignatura";

    }

    // CALENDARIO CURSO

    @GetMapping("calendarioCurso/{id}")
    public String calendarioCurso (@PathVariable("id") long id, Model model) {
        if (servicioCurso.findById(id)!=null)
            return "/jefeEstudios/calendarioCursoDetalles";

        return "/jefeEstudios/cursos";
    }

    @GetMapping("calendarioCursoDetalles")
    public String calendarioCursoDetalles (@ModelAttribute("listaAsignaturas") Curso curso, Model model

            /*, Pair<String, String> horario*/ ) {

        //model.addAttribute("listaAsignaturas", servicioCurso.findAll());
        curso.getAsignaturas();

        for (int i=0; i<0; i++) {
            Asignatura asignatura = curso.getAsignaturas().get(i);
            for (int j=0; j<0; j++) {
                asignatura.getHorarios().get(j);
                for (int k=0; k<0; k++){
                    if (asignatura.getHorarios() == null /*qué pongo aquí*/){}
                }
            }
        }

        //List<Asignatura> asignaturas = new ArrayList<>();

        //curso.getAsignaturas().get(i);
        //horarioDetalle.get(0);

        // List<Asignatura> horarioDetalle = curso.getAsignaturas().get;
        //for (int i=0; i<horarioDetalle.size(); i++) {
        //    List <String> par = (List<String>) horarioDetalle.get(i);
        //    for (int j=0; j<par.size();j++){
        //        String dia = par.get(j);
        //        String hora = par.get(j);
        //    }
        //}

        return "/jefeEstudios/calendarioCursoDetalles";
    }






    // EDITAR

    // TITULO

    @GetMapping("/editTitulo/{id}")
    public String editTituloForm (@PathVariable long id, Model model) {

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

    // Cursos

    @GetMapping("/editCurso/{id}")
    public String editCursoForm (@PathVariable long id, Model model) {
        if (servicioCurso.findById(id) != null){
            model.addAttribute("cursoForm", servicioCurso.findById(id));
            return "/jefeEstudios/formularioCurso";
        }
        return "redirect:/jefeEstudios/";
    }

    @PostMapping("/editCurso/submit")
    public String editCursoSubmit (@ModelAttribute("cursoForm")  Curso editCurso) {
        servicioCurso.edit(editCurso);
        return "redirect:/jefeEstudios/cursos";
    }

    // alumnos
    @GetMapping("/editAlumno/{id}")
    public String editAlumno (@PathVariable long id, Model model) {
        if (servicioAlumno.findById(id) != null) {
            model.addAttribute("alumnoForm", servicioAlumno.findById(id));
            model.addAttribute("listaCursos", servicioCurso.findAll());
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
    public String deleteAlumno (@PathVariable long id, Model model) {
        if (servicioAlumno.findById(id)!=null) {
            servicioAlumno.deleteById(id);
        }
        return "redirect:/jefeEstudios/alumnos";
    }

    @GetMapping("/eliminarProfesor/{id}")
    public String deleteProfesor (@PathVariable long id, @AuthenticationPrincipal Profesor profesor, Model model) {

        if (servicioProfesor.findById(id)!= null /*&& y por qué cuando doy a borrar elimina el de abajo!!!!?
                servicioProfesor.findById(id) != servicioProfesor.findById(profesor.getId())*/) {
            servicioProfesor.deleteById(id);
        }
        return "redirect:/jefeEstudios/profesor";
    }

    @GetMapping("/eliminarTitulo/{id}")
    public String deleteTitulo (@PathVariable long id, Model model) {
        if (servicioTitulo.findById(id)!=null) {
            servicioTitulo.deleteById(id);
        }

        return "redirect:/jefeEstudios/titulos";
    }

    @GetMapping("/eliminarCurso/{id}")
    public String deleteCurso (@PathVariable long id, Model model) {
        if (servicioCurso.findById(id)!=null) {
            servicioCurso.deleteById(id);
        }
        return "redirect:/jefeEstudios/cursos";
    }

    @GetMapping("/eliminarAsignatura/{id}")
    public String deleteAsignatura (@PathVariable long id, Model model) {
        if (servicioAsignatura.findById(id)!=null) {
            servicioAsignatura.deleteById(id);
        }
        return "redirect:/jefeEstudios/asignaturas";
    }


    // Situaciones excepcionales alumnos:
    @GetMapping("/situacionExcepcional")
    public String mostrarSituacionesExcepcionales (Model model) {
            model.addAttribute("listaSituacionesExcepcionales", servicioExcepcional.findAll());
        return "jefeEstudios/situacionExcepcional";
    }

    @GetMapping("/situacionExcepcional/download/{nombre}")
    public ResponseEntity<Resource> descargarFicheros (@PathVariable("nombre") String name){
        Resource resource = fileSystemStorageService.loadAsResource(name);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                resource.getFilename() + "\"").body(resource);
    }

    @GetMapping("/editSituacionExcepcional/{idAlumno}/{idAsignatura}")
    public String editSituacionExcepcional (@PathVariable long idAlumno, @PathVariable long idAsignatura,
                                             Model model){
        SituacionExcepcionalPK pk = new SituacionExcepcionalPK (idAlumno,idAsignatura);
        if (servicioExcepcional.findById(pk)!=null){
            model.addAttribute("excepcionalForm", servicioExcepcional.findById(pk));
            return "jefeEstudios/formularioSituacionExcepcional";
        }
        return "redirect:/jefeEstudios/";
    }

    @PostMapping("/editSituacionExcepcional/submit")
    public String editSituacionEscepcionalSubmit (@ModelAttribute("excepcionalForm") SituacionExcepcional excepcional){
        excepcional.setFechaResolucion(LocalDate.now());
        servicioExcepcional.edit(excepcional);
        return "redirect:/jefeEstudios/situacionExcepcional";
    }



}
