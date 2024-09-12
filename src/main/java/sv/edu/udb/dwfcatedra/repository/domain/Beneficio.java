package sv.edu.udb.dwfcatedra.repository.domain;

import javax.persistence.*;
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

public class Beneficio {

    @Id //definicion del id (es una anotación obligatoria si se usa entity)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //anotación que define como se va a generar automaticamente el valor de id
    private Long id;

    @Column(nullable = false)
    private int planId;

    @Column(nullable = false)
    private String descripcion;

}
