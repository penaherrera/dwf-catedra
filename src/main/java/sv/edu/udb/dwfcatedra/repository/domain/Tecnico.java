package sv.edu.udb.dwfcatedra.repository.domain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Tecnico extends Miembro {

    @Column(nullable = false)
    private String cargo;
}
