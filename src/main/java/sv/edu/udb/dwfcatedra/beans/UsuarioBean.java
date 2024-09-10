package sv.edu.udb.dwfcatedra.beans;

import sv.edu.udb.dwfcatedra.repository.domain.Usuario;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Named
@RequestScoped
public class UsuarioBean implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    private Usuario usuario = new Usuario();

    // ID del usuario de prueba
    private final Long testUserId = 1L; // Cambia esto al ID real del usuario de prueba en tu base de datos

    public Usuario getUsuario() {
        if (usuario.getId() == null) {
            // Cargar el usuario de prueba si aún no se ha cargado
            usuario = entityManager.find(Usuario.class, testUserId);
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
