package sv.edu.udb.dwfcatedra.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.udb.dwfcatedra.repository.UsuarioRepository;
import sv.edu.udb.dwfcatedra.repository.domain.Usuario;

@Named
@RequestScoped
public class UsuarioService {

    @Inject
    private UsuarioRepository usuarioRepository;

    @Inject
    private SessionService sessionService;

    public Usuario findUsuarioByEmailAndPassword(String email, String password) {
        return usuarioRepository.findByEmailAndPassword(email, password);
    }

    public void saveUsuario(Usuario usuario) {

        usuarioRepository.save(usuario);
        sessionService.setUsuarioRol(0);


    }
    
}
