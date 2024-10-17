package sv.edu.udb.dwfcatedra.service;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Named
@SessionScoped
public class SessionService implements Serializable {

    @Getter @Setter
    private Integer usuarioRol;


}
