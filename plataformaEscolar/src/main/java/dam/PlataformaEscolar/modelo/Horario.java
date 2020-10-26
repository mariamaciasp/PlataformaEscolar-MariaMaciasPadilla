package dam.PlataformaEscolar.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data @Builder
@Entity
@AllArgsConstructor @NoArgsConstructor
public class Horario {

    @Id @GeneratedValue
    private long id;

    private String dia;
    private int hora;

    @ManyToOne
    private Asignatura asignatura;

    public Horario(String dia, int hora) {
        this.dia = dia;
        this.hora = hora;
    }

    public Horario(String dia) {
        this.dia = dia;
    }

    public Horario(int hora) {
        this.hora = hora;
    }


}
