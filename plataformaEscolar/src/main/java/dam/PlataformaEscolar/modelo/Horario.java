package dam.PlataformaEscolar.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @Builder
@Entity
@AllArgsConstructor @NoArgsConstructor
public class Horario {

    @Id @GeneratedValue
    private long id;

    private int dia;
    private int hora;

    @ManyToOne(fetch = FetchType.EAGER)
    private Asignatura asignatura;

    public Horario(int dia, int hora) {
        this.dia = dia;
        this.hora = hora;
    }

    public Horario(int dia, int hora, Asignatura asignatura) {
        this.dia = dia;
        this.hora = hora;
        this.asignatura = asignatura;
    }

    public int compareTo (Horario horario) {
        if (this.getHora()<horario.getHora()){
            return 1;
        }else{
            return -1;
        }
    }


}
