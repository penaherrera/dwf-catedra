package sv.edu.udb.dwfcatedra.repository.domain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Tecnico extends Miembro {

    @Column(nullable = false)
    private String cargo;
}
