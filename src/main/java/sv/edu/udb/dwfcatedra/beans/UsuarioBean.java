package sv.edu.udb.dwfcatedra.beans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import sv.edu.udb.dwfcatedra.repository.domain.Usuario;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.udb.dwfcatedra.service.SessionService;
import sv.edu.udb.dwfcatedra.service.UsuarioService;

import java.io.Serializable;

@Named
@RequestScoped
public class UsuarioBean implements Serializable {

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private SessionService sessionService;

    @Getter @Setter
    private String nombre;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;


    public String ingresarUsuario() {
        Usuario usuarioExistente = usuarioService.findUsuarioByEmailAndPassword(email, password);

        if (usuarioExistente != null) {
            sessionService.setUsuarioRol(usuarioExistente.getRol());
            return "index.xhtml?faces-redirect=true";
        } else {
            return "signup.xhtml?faces-redirect=true";
        }
    }

    public String registrarUsuario() {

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPassword(password);
        nuevoUsuario.setRol(0);

        usuarioService.saveUsuario(nuevoUsuario);

        return "login.xhtml?faces-redirect=true";
    }

    public String cerrarSesion(){
        sessionService.setUsuarioRol(null);
        return "index.xhtml?faces-redirect=true";
    }
}

