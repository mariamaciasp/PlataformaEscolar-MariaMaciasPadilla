package dam.PlataformaEscolar;

import dam.PlataformaEscolar.modelo.*;
import dam.PlataformaEscolar.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PlataformaEscolarApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlataformaEscolarApplication.class, args);
	}


	@Bean
	public CommandLineRunner init(UsuarioServicio servicioUsuario, ProfesorServicio servicioProfesor,
								  AlumnoServicio servicioAlumno, TituloService servicioTitulo,
								  CursoServicio servicioCurso, AsignaturaServicio servicioAsignatura,
								  HorarioService servicioHorario,
								  EnvioEmail envioEmail, PasswordEncoder passwordEncoder) {
		return args -> {





			// instancia de titulos
			
			Titulo dam = new Titulo("Desarrollo de aplicaciones multiplataforma","DAM");
			Titulo ayf = new Titulo("Administración y finanzas","AYF");
			Titulo ga = new Titulo("Gestión administrativa","GA");

			List<Titulo> listaTitulos = Arrays.asList(dam,ayf,ga);

			for (Titulo t: listaTitulos) {
				servicioTitulo.save(t);
			}

			// instancia Cursos

			Curso c1 = new Curso ("1º Desarrollo de aplicaciones multiplataforma", "1º DAM",dam);
			Curso c2 = new Curso ("2º Desarrollo de aplicaciones multiplataforma", "2º DAM",dam);
			Curso c3 = new Curso ("1º Administración y finanzas", "1º AYF", ayf);
			Curso c4 = new Curso ("2º Administración y finanzas", "2º AYF", ayf);
			Curso c5 = new Curso ("1º Gestión administrativa", "1º GA", ga);
			Curso c6 = new Curso ("2º Gestión administrativa", "2º GA", ga);

			List<Curso> listaCursos = Arrays.asList(c1,c2,c3,c4,c5,c6);

			for (Curso c:
				listaCursos) {
				servicioCurso.save(c);
			}




			// Asignaturas
			Asignatura sge = new Asignatura("Sistemas de gestión empresarial", "SGE",c2);
			Asignatura di = new Asignatura("Diseño de interfaces", "DI", c2);
			Asignatura bbdd =  new Asignatura("Base de datos","BBDD",c1);
			/*di.getHorarios().add(Pair.of("Lunes","1"));
			di.getHorarios().add(Pair.of("Martes","5"));
			sge.getHorarios().add(Pair.of("Lunes","1"));
			sge.getHorarios().add(Pair.of("Martes","2"));
			sge.getHorarios().add(Pair.of("Jueves","1"));*/

			//envioEmail.sendEmail(alumno, "Hola caracola", "Esto es una prueba chata");
			servicioAsignatura.save(sge);
			servicioAsignatura.save(di);
			servicioAsignatura.save(bbdd);

			// Horarios
			Horario horario1 = new Horario(1,1);
			Horario horario2 = new Horario(2,2);
			Horario horario3 = new Horario(1,2);
			Horario horario4 = new Horario(4,1);
			Horario horario5 = new Horario(5,5);
			Horario horario6 = new Horario(5,6);

			horario1.setAsignatura(sge);
			horario2.setAsignatura(sge);
			horario3.setAsignatura(di);
			horario4.setAsignatura(di);
			horario5.setAsignatura(bbdd);
			horario6.setAsignatura(bbdd);



			// instancia un usuario de cada
			Profesor jefeEstudios = new Profesor ("Ángel", "Naranjo","admin",passwordEncoder.encode("1234"),null,true ,true);
			Profesor profesor = new Profesor("Luismi", "Lopez","lmlopez",passwordEncoder.encode("1234"), null,true,false);
			Alumno alumno = new Alumno ("María", "García", "m@gmail.com",passwordEncoder.encode("1234"),null,true);

			alumno.setCurso(c1);
			servicioProfesor.save(jefeEstudios);
			servicioProfesor.save(profesor);
			servicioAlumno.save(alumno);


		};
	}

}
