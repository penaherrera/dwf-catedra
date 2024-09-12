package sv.edu.udb.dwfcatedra.repository.domain;

import javax.persistence.*;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity


public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String lugar;

    @Column(nullable = false)
    private String tipo;

}
