package dam.PlataformaEscolar.controladores;

import dam.PlataformaEscolar.modelo.Titulo;
import dam.PlataformaEscolar.service.CursoServicio;
import dam.PlataformaEscolar.service.TituloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/jefeEstudios")
public class JefeEstudiosController {

    @Autowired
    private TituloService servicioTitulo;
    @Autowired
    private CursoServicio servicioCurso;

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





}
