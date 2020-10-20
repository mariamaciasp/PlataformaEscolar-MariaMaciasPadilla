package dam.PlataformaEscolar.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements UserDetails{ // implements UserDetails y poner la herencia bien

    private static final long serialVersionUID = 1409538586158223652L; // para que es???

    @Id @GeneratedValue
    private long id;

    private String nombre;
    private String apellidos;

    @Column(unique=true)
    private String email;

    private String password;

    private String codigoActivacion;
    private boolean activado;

    public Usuario(String nombre, String apellidos, String email, String password, String codigoActivacion,
                   boolean activado) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.codigoActivacion = codigoActivacion;
        this.activado = activado;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
