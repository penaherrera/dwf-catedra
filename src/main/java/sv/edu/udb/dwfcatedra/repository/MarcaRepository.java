package sv.edu.udb.dwfcatedra.repository;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.udb.dwfcatedra.repository.domain.Marca;
import java.util.List;

@Named
public class MarcaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Marca> findAll() {
        final String QUERY = "select m from Marca m";
        return entityManager
                .createQuery(QUERY, Marca.class)
                .getResultList();
    }
}
