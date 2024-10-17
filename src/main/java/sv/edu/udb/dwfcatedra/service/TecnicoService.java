package sv.edu.udb.dwfcatedra.service;

import jakarta.ejb.Stateless;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import sv.edu.udb.dwfcatedra.repository.TecnicoRepository;
import sv.edu.udb.dwfcatedra.repository.domain.Tecnico;

import java.util.List;

@Named
@RequestScoped
@NoArgsConstructor
public class TecnicoService {

    @Inject
    private TecnicoRepository tecnicoRepository;

    // Obtener todos los técnicos
    public List<Tecnico> getAllTecnicos() {
        return tecnicoRepository.findAll();
    }

    // Guardar un nuevo técnico
    @Transactional
    public void saveTecnico(Tecnico tecnico) {
        tecnicoRepository.save(tecnico);
    }

    // Actualizar un técnico existente
    @Transactional
    public void updateTecnico(Tecnico tecnico) {
        tecnicoRepository.update(tecnico);
    }

    // Eliminar un técnico por ID
    @Transactional
    public void deleteTecnicoById(Long id) {
        tecnicoRepository.deleteById(id);
    }

    // Obtener un técnico por ID (si necesitas esta función)
    public Tecnico getTecnicoById(Long id) {
        return tecnicoRepository.findById(id);
    }
}
