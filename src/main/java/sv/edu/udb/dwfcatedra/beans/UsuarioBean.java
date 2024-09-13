package sv.edu.udb.dwfcatedra.beans;

import sv.edu.udb.dwfcatedra.repository.domain.Usuario;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;

@Named
@RequestScoped
public class UsuarioBean implements Serializable {

}
