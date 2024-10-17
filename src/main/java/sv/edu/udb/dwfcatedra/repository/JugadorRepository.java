package sv.edu.udb.dwfcatedra.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sv.edu.udb.dwfcatedra.repository.domain.Jugador;

import java.util.List;

public class JugadorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Obtener todos los jugadores
    public List<Jugador> findAll() {
        return entityManager.createQuery("SELECT j FROM Jugador j", Jugador.class).getResultList();
    }

    // Guardar un nuevo jugador
    @Transactional
    public void save(Jugador jugador) {
        entityManager.persist(jugador);
    }

    // Actualizar un jugador existente
    @Transactional
    public void update(Jugador jugador) {
        entityManager.merge(jugador);
    }

    // Eliminar un jugador por ID
    @Transactional
    public void deleteById(Long id) {
        Jugador jugador = entityManager.find(Jugador.class, id);
        if (jugador != null) {
            entityManager.remove(jugador);
        }
    }

    // Obtener un jugador por ID
    public Jugador findById(Long id) {
        return entityManager.find(Jugador.class, id);
    }
}
