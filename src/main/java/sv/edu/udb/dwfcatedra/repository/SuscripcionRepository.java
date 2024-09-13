package sv.edu.udb.dwfcatedra.repository;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.udb.dwfcatedra.repository.domain.Suscripcion;
import java.util.List;

@Named
public class SuscripcionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Suscripcion> findAll() {
        final String QUERY = "select s from Suscripcion s";
        return entityManager
                .createQuery(QUERY, Suscripcion.class)
                .getResultList();
    }
}
