package sv.edu.udb.dwfcatedra.repository;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sv.edu.udb.dwfcatedra.repository.domain.Usuario;

@Named
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Usuario usuario) {
        entityManager.persist(usuario);
    }

    @Transactional
    public Usuario findByEmailAndPassword(String email, String password) {
        if ((email == null) || (email.trim().isEmpty()) || (password == null) || (password.trim().isEmpty())) {
            return null;
        }
        try {
            return entityManager.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.password = :password", Usuario.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
