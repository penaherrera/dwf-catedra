package sv.edu.udb.dwfcatedra.repository.domain;
import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Jugador extends Miembro {

    @Column(nullable = false)
    private String posicion;
    @Column(nullable = false)
    private int dorsal;
}
