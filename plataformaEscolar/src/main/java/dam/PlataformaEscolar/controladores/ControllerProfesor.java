package dam.PlataformaEscolar.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profesor")
public class ControllerProfesor {


    @GetMapping({"/","inicio"})
    public String inicioProfesor() {
        return "/profesor/inicioProfesor";
    }

}
