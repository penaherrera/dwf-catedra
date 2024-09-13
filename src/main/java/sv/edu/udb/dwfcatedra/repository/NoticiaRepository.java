package sv.edu.udb.dwfcatedra.repository;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    public Noticia findById(final Long id) {
        return entityManager.find(Noticia.class, id);
    }

}
