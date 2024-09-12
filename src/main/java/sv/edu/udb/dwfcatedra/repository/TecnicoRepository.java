package sv.edu.udb.dwfcatedra.repository;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.udb.dwfcatedra.repository.domain.Tecnico;
import java.util.List;

@Named
public class TecnicoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Tecnico> findAll() {
        final String QUERY = "select t from Tecnico t";
        return entityManager
                .createQuery(QUERY, Tecnico.class)
                .getResultList();
    }
}
