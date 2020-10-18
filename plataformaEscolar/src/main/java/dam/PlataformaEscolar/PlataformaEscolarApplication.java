package dam.PlataformaEscolar;

import dam.PlataformaEscolar.modelo.Alumno;
import dam.PlataformaEscolar.modelo.Profesor;
import dam.PlataformaEscolar.service.AlumnoServicio;
import dam.PlataformaEscolar.service.ProfesorServicio;
import dam.PlataformaEscolar.service.UsuarioServicio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PlataformaEscolarApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlataformaEscolarApplication.class, args);
	}


	@Bean
	public CommandLineRunner init(UsuarioServicio servicioUsuario, ProfesorServicio servicioProfesor,
								  AlumnoServicio servicioAlumno, PasswordEncoder passwordEncoder) {
		return args -> {

			Profesor jefeEstudios = new Profesor ("Ángel", "Naranjo","admin",passwordEncoder.encode("1234"), true);
			Profesor profesor = new Profesor("Luismi", "Lopez","lmlopez",passwordEncoder.encode("1234"), false);
			Alumno alumno = new Alumno ("María", "Macías", "mmacias", passwordEncoder.encode("1234"));

			servicioProfesor.save(jefeEstudios);
			servicioProfesor.save(profesor);
			servicioAlumno.save(alumno);



		};
	}

}
