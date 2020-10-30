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
			Asignatura pmdm = new Asignatura("Programación Multimedia y Dispositivos Móviles", "PMDM", c2);
			Asignatura ad = new Asignatura("Acceso a datos", "AD", c2);
			Asignatura empresa = new Asignatura("Empresa e iniciativa emprendedora", "EIE", c2);
			Asignatura ingles = new Asignatura("Inglés", "Ingles", c2);
			Asignatura psp = new Asignatura("Programación de Servicios y Procesos", "PSP", c2);
			Asignatura fop = new Asignatura("Formación y orientación personal", "FOP", c2);

			Asignatura bbdd =  new Asignatura("Base de datos","BBDD",c1);
			/*di.getHorarios().add(Pair.of("Lunes","1"));
			di.getHorarios().add(Pair.of("Martes","5"));
			sge.getHorarios().add(Pair.of("Lunes","1"));
			sge.getHorarios().add(Pair.of("Martes","2"));
			sge.getHorarios().add(Pair.of("Jueves","1"));*/

			//envioEmail.sendEmail(alumno, "Hola caracola", "Esto es una prueba chata");
			servicioAsignatura.save(sge);
			servicioAsignatura.save(di);
			servicioAsignatura.save(pmdm);
			servicioAsignatura.save(ad);
			servicioAsignatura.save(empresa);
			servicioAsignatura.save(ingles);
			servicioAsignatura.save(psp);
			servicioAsignatura.save(fop);
			servicioAsignatura.save(bbdd);

			// Horarios
			Horario h11 = new Horario(1,1);
			Horario h12 = new Horario(1,2);
			Horario h13 = new Horario(1,3);
			Horario h14 = new Horario(1,4);
			Horario h15 = new Horario(1,5);
			Horario h16 = new Horario(1,6);

			Horario h21 = new Horario(2,1);
			Horario h22 = new Horario(2,2);
			Horario h23 = new Horario(2,3);
			Horario h24 = new Horario(2,4);
			Horario h25 = new Horario(2,5);
			Horario h26 = new Horario(2,6);

			Horario h31 = new Horario(3,1);
			Horario h32 = new Horario(3,2);
			Horario h33 = new Horario(3,3);
			Horario h34 = new Horario(3,4);
			Horario h35 = new Horario(3,5);
			Horario h36 = new Horario(3,6);

			Horario h41 = new Horario(4,1);
			Horario h42 = new Horario(4,2);
			Horario h43 = new Horario(4,3);
			Horario h44 = new Horario(4,4);
			Horario h45 = new Horario(4,5);
			Horario h46 = new Horario(4,6);

			Horario h51 = new Horario(5,1);
			Horario h52 = new Horario(5,2);
			Horario h53 = new Horario(5,3);
			Horario h54 = new Horario(5,4);
			Horario h55 = new Horario(5,5);
			Horario h56 = new Horario(5,6);

			h11.setAsignatura(ad);
			h12.setAsignatura(pmdm);
			h13.setAsignatura(sge);
			h14.setAsignatura(di);
			h15.setAsignatura(di);
			h16.setAsignatura(empresa);

			h21.setAsignatura(psp);
			h22.setAsignatura(di);
			h23.setAsignatura(di);
			h24.setAsignatura(empresa);
			h25.setAsignatura(ingles);
			h26.setAsignatura(pmdm);

			h31.setAsignatura(di);
			h32.setAsignatura(psp);
			h33.setAsignatura(ingles);
			h34.setAsignatura(empresa);
			h35.setAsignatura(sge);
			h36.setAsignatura(ad);

			h41.setAsignatura(sge);
			h42.setAsignatura(fop);
			h43.setAsignatura(ad);
			h44.setAsignatura(ingles);
			h45.setAsignatura(pmdm);
			h46.setAsignatura(di);

			h51.setAsignatura(ad);
			h52.setAsignatura(sge);
			h53.setAsignatura(psp);
			h54.setAsignatura(di);
			h55.setAsignatura(empresa);
			h56.setAsignatura(pmdm);

			List<Horario> listaHorarios =
					Arrays.asList(h11,h12,h13,h14,h15,h16,
								  h21,h22,h23,h24,h25,h26,
								  h31,h32,h33,h34,h35,h36,
							      h41,h42,h43,h44,h45,h46,
							      h51,h52,h53,h54,h55,h56);

			for (Horario h: listaHorarios) {
				servicioHorario.save(h);
			}

			servicioHorario.save(h11);
			servicioHorario.save(h12);
			servicioHorario.save(h13);
			servicioHorario.save(h14);
			servicioHorario.save(h15);
			servicioHorario.save(h16);


			// instancia un usuario de cada
			Profesor jefeEstudios = new Profesor ("Ángel", "Naranjo","admin",passwordEncoder.encode("1234"),null,true ,true);
			Profesor profesor1 = new Profesor("Luis Miguel", "Lopez","lopez@gmail.com",passwordEncoder.encode("1234"), null,true,false);
			Profesor profesor2 = new Profesor("Miguel", "Campos","campos@gmail.com",passwordEncoder.encode("1234"), null,true,false);
			Alumno alumno1 = new Alumno ("María", "Macías Padilla", "maria@gmail.com",passwordEncoder.encode("1234"),null,true);
			Alumno alumno2 = new Alumno ("Iván", "Muñoz Martín", "ivan@gmail.com",passwordEncoder.encode("1234"),null,true);
			Alumno alumno3 = new Alumno ("Estefanía", "Pino Rebollo", "estefania@gmail.com",passwordEncoder.encode("1234"),null,true);
			Alumno alumno4 = new Alumno ("Gloria", "Padilla Rodríguez", "gloria@gmail.com",passwordEncoder.encode("1234"),null,true);

			alumno1.setCurso(c2);
			alumno2.setCurso(c2);
			alumno3.setCurso(c2);
			alumno4.setCurso(c2);

			servicioProfesor.save(jefeEstudios);
			servicioProfesor.save(profesor1);
			servicioProfesor.save(profesor2);
			servicioAlumno.save(alumno1);
			servicioAlumno.save(alumno2);
			servicioAlumno.save(alumno3);
			servicioAlumno.save(alumno4);


		};
	}

}
