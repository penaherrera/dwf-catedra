package sv.edu.udb.dwfcatedra.repository;

import sv.edu.udb.dwfcatedra.repository.domain.Jugador;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

public class JugadorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Jugador> findAll() {
        final String QUERY = "select j from Jugador j";
        return entityManager
                .createQuery(QUERY, Jugador.class)
                .getResultList();
    }
}
