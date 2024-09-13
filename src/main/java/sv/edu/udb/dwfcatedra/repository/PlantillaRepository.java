package sv.edu.udb.dwfcatedra.repository;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
