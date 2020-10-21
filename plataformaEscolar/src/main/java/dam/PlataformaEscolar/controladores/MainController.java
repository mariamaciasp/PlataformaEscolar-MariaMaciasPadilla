package dam.PlataformaEscolar.controladores;

import dam.PlataformaEscolar.modelo.Usuario;
import dam.PlataformaEscolar.modelo.ValidacionUsuario;
import dam.PlataformaEscolar.service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    UsuarioServicio servicioUsuario;

    @GetMapping({"/","/inicio"})
    public String inicio() {
        return "inicio";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/activacion")
    public String activacion(Model model){
        model.addAttribute("formActivacion", new ValidacionUsuario());
        return "activacion";
    }

    @PostMapping("/activacion/submit")
    public String activacionSubmit(@ModelAttribute("formActivacion") ValidacionUsuario usuarioValidacion) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<Usuario> usuarioV;

        usuarioV = servicioUsuario.buscarPorEmail(usuarioValidacion.getEmail());
        Usuario usuario = servicioUsuario.findById(usuarioV.get().getId());

        if (usuario.getCodigoActivacion().equals(usuarioValidacion.getCodigoActivacion()) &&
                !usuario.isActivado()){
            usuario.setPassword(encoder.encode(usuarioValidacion.getPassword()));
            usuario.setActivado(true);
            usuario.setCodigoActivacion(null);

            servicioUsuario.edit(usuario);
            return "redirect:/login";
        }
        return "redirect:/activacion";
    }





}
