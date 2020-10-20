package dam.PlataformaEscolar.controladores;

import dam.PlataformaEscolar.modelo.*;
import dam.PlataformaEscolar.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


    @ModelAttribute("listaTitulos")
    public List<Titulo> listaCategorias () {
        return servicioTitulo.findAll();
    }


    @GetMapping("/")
    public String inicioJefeEstudios () {
        return "jefeEstudios/inicioJE";
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


    // registro alumno
    @GetMapping("/registroAlumno")
    public String nuevoClienteForm (Model model) {
        model.addAttribute("alumnoForm", new Alumno());
        model.addAttribute("listaCursos", servicioCurso.findAll());
        return "jefeEstudios/formularioAlumno";
    }

    @PostMapping("/nuevoAlumno/submit")
    public String registroAlumno (@ModelAttribute("alumnoForm") Alumno nuevoAlumno) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        nuevoAlumno.setPassword(encoder.encode(nuevoAlumno.getPassword()));
        servicioAlumno.save(nuevoAlumno);
        envioEmail.sendEmail(nuevoAlumno, "Alta cuenta","Su código de alta en " +
                "Triana eschool es " + servicioUsuario.codigoAleatorio());

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

    // registro profesores

    @GetMapping("/registroProfesor")
    public String nuevoProfesorForm (Model model) {
        model.addAttribute("profesorForm", new Profesor());
        return "jefeEstudios/formularioProfesor";
    }

    @PostMapping("/nuevoProfesor/submit")
    public String registroProfesor (@ModelAttribute("profesorForm") Profesor nuevoProfesor) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        nuevoProfesor.setPassword(encoder.encode(nuevoProfesor.getPassword()));
        servicioProfesor.save(nuevoProfesor);
        envioEmail.sendEmail(nuevoProfesor, "Alta cuenta","Su código de alta en " +
                "Triana eschool es " + servicioUsuario.codigoAleatorio());
        return "redirect:/jefeEstudios/";

    }






}
