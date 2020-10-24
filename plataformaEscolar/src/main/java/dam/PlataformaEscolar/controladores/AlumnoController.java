package dam.PlataformaEscolar.controladores;

import dam.PlataformaEscolar.modelo.Alumno;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {


    @GetMapping({"/", "/inicio"})
    public String inicioAlumno (){
        return "alumno/inicioAlumno";

    }

    @GetMapping("/asignaturas")
    public String listaAsignaturasAlumno (@AuthenticationPrincipal Alumno alumno, Model model) {
        model.addAttribute("listaAsignaturas", alumno.getCurso().getAsignaturas());
        return "alumno/asignaturasAlumno";
    }


}
