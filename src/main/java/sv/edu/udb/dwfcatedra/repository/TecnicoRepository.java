package sv.edu.udb.dwfcatedra.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sv.edu.udb.dwfcatedra.repository.domain.Tecnico;

import java.util.List;

public class TecnicoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Obtener todos los técnicos
    public List<Tecnico> findAll() {
        return entityManager.createQuery("SELECT t FROM Tecnico t", Tecnico.class).getResultList();
    }

    // Guardar un nuevo técnico
    @Transactional
    public void save(Tecnico tecnico) {
        entityManager.persist(tecnico);
    }

    // Actualizar un técnico existente
    @Transactional
    public void update(Tecnico tecnico) {
        entityManager.merge(tecnico);
    }

    // Eliminar un técnico por ID
    @Transactional
    public void deleteById(Long id) {
        Tecnico tecnico = entityManager.find(Tecnico.class, id);
        if (tecnico != null) {
            entityManager.remove(tecnico);
        }
    }

    // Obtener un técnico por ID
    public Tecnico findById(Long id) {
        return entityManager.find(Tecnico.class, id);
    }
}
