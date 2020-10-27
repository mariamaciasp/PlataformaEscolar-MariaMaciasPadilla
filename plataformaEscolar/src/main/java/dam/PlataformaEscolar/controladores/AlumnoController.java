package dam.PlataformaEscolar.controladores;

import dam.PlataformaEscolar.modelo.Alumno;
import dam.PlataformaEscolar.modelo.Asignatura;
import dam.PlataformaEscolar.modelo.FormularioSituacionExcepcional;
import dam.PlataformaEscolar.modelo.SituacionExcepcional;
import dam.PlataformaEscolar.service.AlumnoServicio;
import dam.PlataformaEscolar.service.AsignaturaServicio;
import dam.PlataformaEscolar.service.SituacionExcepcionalService;
import dam.PlataformaEscolar.upload.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.time.LocalDate;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {


    @Autowired
    private AsignaturaServicio servicioAsignatura;
    @Autowired
    private SituacionExcepcionalService servicioExcepcional;
    @Autowired
    private StorageService storageService;
    @Autowired
    private AlumnoServicio servicioAlumno;


    @GetMapping({"/", "/inicio"})
    public String inicioAlumno (){
        return "alumno/inicioAlumno";

    }

    @GetMapping("/asignaturas")
    public String listaAsignaturasAlumno (@AuthenticationPrincipal Alumno alumno, Model model) {
        model.addAttribute("listaAsignaturas", alumno.getCurso().getAsignaturas());
        return "alumno/asignaturasAlumno";
    }

    // convalidar

    @GetMapping("/convalidacion")
    public String convalidadAsignatura (Model model, @AuthenticationPrincipal Alumno alumno) {
        model.addAttribute("convalidacionForm", new FormularioSituacionExcepcional());
        model.addAttribute("listaAsignaturas", servicioAlumno.findById(alumno.getId()).getCurso().getAsignaturas());

        return "alumno/formularioConvalidacion";
    }

    @PostMapping("/convalidacion/submit")
    public String convalidarAsignaturaSubmit (@ModelAttribute("convalidacionForm") FormularioSituacionExcepcional formularioExcepcional,
                                              @AuthenticationPrincipal Alumno alumno,
                                              @RequestParam("file") MultipartFile file) {

        String adjunto = storageService.store(file, alumno.getId());
        Asignatura asignatura = servicioAsignatura.findById(formularioExcepcional.getIdAsignatura());
        SituacionExcepcional excepcional = new SituacionExcepcional();
        excepcional.setAdjunto(MvcUriComponentsBuilder.fromMethodName(AlumnoController.class,
                "serveFile", adjunto).build().toUriString());
        excepcional.setAsignatura(asignatura);
        excepcional.setAlumno(alumno);
        excepcional.setFechaSolicitud(LocalDate.now());
        excepcional.setTipo("Convalidación");
        excepcional.setEstado("Pendiente");
        servicioExcepcional.save(excepcional);

        return "redirect:/alumno/";
    }


    // exencion

    @GetMapping("/exencion")
    public String exencionAsignatura (Model model, @AuthenticationPrincipal Alumno alumno) {
        model.addAttribute("exencionForm", new FormularioSituacionExcepcional());
        model.addAttribute("listaAsignaturas", servicioAlumno.findById(alumno.getId()).getCurso().getAsignaturas());

        return "alumno/formularioExencion";
    }

    @PostMapping("/exencion/submit")
    public String exencionAsignaturaSubmit (@ModelAttribute("exencionForm") FormularioSituacionExcepcional formularioExcepcional,
                                              @AuthenticationPrincipal Alumno alumno,
                                              @RequestParam("file") MultipartFile file) {

        String adjunto = storageService.store(file, alumno.getId());
        Asignatura asignatura = servicioAsignatura.findById(formularioExcepcional.getIdAsignatura());
        SituacionExcepcional excepcional = new SituacionExcepcional();
        excepcional.setAdjunto(MvcUriComponentsBuilder.fromMethodName(AlumnoController.class,
                "serveFile", adjunto).build().toUriString());
        excepcional.setAsignatura(asignatura);
        excepcional.setAlumno(alumno);
        excepcional.setFechaSolicitud(LocalDate.now());
        excepcional.setTipo("Exención");
        excepcional.setEstado("Pendiente");
        servicioExcepcional.save(excepcional);

        return "redirect:/alumno/";
    }



    // subida de archivos


    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().body(file);
    }


}
