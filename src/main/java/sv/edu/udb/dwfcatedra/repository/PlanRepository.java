package sv.edu.udb.dwfcatedra.repository;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.udb.dwfcatedra.repository.domain.Plan;
import java.util.List;

@Named
public class PlanRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Plan> findAll() {
        final String QUERY = "select p from Plan p";
        return entityManager
                .createQuery(QUERY, Plan.class)
                .getResultList();
    }
}
