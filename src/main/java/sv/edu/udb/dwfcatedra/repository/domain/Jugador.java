package sv.edu.udb.dwfcatedra.repository.domain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Jugador extends Miembro {

    @Column(nullable = false)
    private String posicion;

    @Column(nullable = false)
    private int dorsal;

}
