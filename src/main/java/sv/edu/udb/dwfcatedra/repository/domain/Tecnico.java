package sv.edu.udb.dwfcatedra.repository.domain;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Tecnico extends Miembro {

    @Column(nullable = false)
    private String cargo;
}
