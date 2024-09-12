package sv.edu.udb.dwfcatedra.repository;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
