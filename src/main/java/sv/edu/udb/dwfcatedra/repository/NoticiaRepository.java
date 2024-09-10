package sv.edu.udb.dwfcatedra.repository;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.udb.dwfcatedra.repository.domain.Noticia;
import java.util.List;

@Named
public class NoticiaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Noticia> findAll() {
        final String QUERY = "select n from Noticia n";
        return entityManager
                .createQuery(QUERY, Noticia.class)
                .getResultList();
    }
}
