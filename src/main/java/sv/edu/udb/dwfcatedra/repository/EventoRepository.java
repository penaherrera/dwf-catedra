package sv.edu.udb.dwfcatedra.repository;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
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

    public long count() {
        Query query = entityManager.createQuery("SELECT COUNT(e) FROM Evento e");
        return (Long) query.getSingleResult();
    }

    public Evento findById(final Long id) {
        return entityManager.find(Evento.class, id);
    }

    @Transactional
    public void save(final Evento evento) {
        entityManager.persist(evento);
    }

    @Transactional
    public void update(final Evento evento) {
        entityManager.merge(evento);
    }

    @Transactional
    public void delete(Evento evento) {
        if (entityManager.contains(evento)) {
            entityManager.remove(evento);
        } else {
            Evento mergedEvento = entityManager.merge(evento);
            entityManager.remove(mergedEvento);
        }
    }
}
