package sv.edu.udb.dwfcatedra.repository;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.udb.dwfcatedra.repository.domain.Evento;
import java.util.List;

@Named
public class EventoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Evento> findAll() {
        final String QUERY = "select e from Evento e";
        return entityManager
                .createQuery(QUERY, Evento.class)
                .getResultList();
    }
}
