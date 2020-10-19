package dam.PlataformaEscolar.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jefeEstudios")
public class JefeEstudiosController {

    @GetMapping("/")
    public String inicioJefeEstudios () {
        return "jefeEstudios/inicioJE";
    }



}
