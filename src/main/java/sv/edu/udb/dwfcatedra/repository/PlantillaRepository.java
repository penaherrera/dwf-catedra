package sv.edu.udb.dwfcatedra.repository;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.udb.dwfcatedra.repository.domain.Plantilla;
import java.util.List;

@Named
public class PlantillaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Plantilla> findAll() {
        final String QUERY = "select p from Plantilla p";
        return entityManager
                .createQuery(QUERY, Plantilla.class)
                .getResultList();
    }
}
