package sv.edu.udb.dwfcatedra.repository;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.udb.dwfcatedra.repository.domain.Beneficio;
import java.util.List;

@Named
public class BeneficioRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Beneficio> findAll() {
        final String QUERY = "select b from Beneficio b";
        return entityManager
                .createQuery(QUERY, Beneficio.class)
                .getResultList();
    }
}
