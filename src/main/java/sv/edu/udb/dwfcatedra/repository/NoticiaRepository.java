package sv.edu.udb.dwfcatedra.repository;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
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

    public long count() {
        Query query = entityManager.createQuery("SELECT COUNT(n) FROM Noticia n");
        return (Long) query.getSingleResult();
    }

    public Noticia findById(final Long id) {
        return entityManager.find(Noticia.class, id);
    }

    @Transactional
    public void save(final Noticia noticia) {
        entityManager.persist(noticia);
    }

    @Transactional
    public void update(final Noticia noticia) {
        entityManager.merge(noticia);
    }

    @Transactional
    public void delete(Noticia noticia) {
        if (entityManager.contains(noticia)) {
            entityManager.remove(noticia);
        } else {
            Noticia mergedNoticia = entityManager.merge(noticia);
            entityManager.remove(mergedNoticia);
        }
    }


}
