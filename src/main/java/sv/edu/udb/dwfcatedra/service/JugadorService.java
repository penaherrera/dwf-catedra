package sv.edu.udb.dwfcatedra.service;

import jakarta.ejb.Stateless;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import sv.edu.udb.dwfcatedra.repository.JugadorRepository;
import sv.edu.udb.dwfcatedra.repository.domain.Jugador;

import java.util.List;

@Named
@RequestScoped
@NoArgsConstructor
public class JugadorService {

    @Inject
    private JugadorRepository jugadorRepository;

    // Obtener todos los jugadores
    public List<Jugador> getAllJugadores() {
        return jugadorRepository.findAll();
    }

    // Guardar un nuevo jugador
    @Transactional
    public void saveJugador(Jugador jugador) {
        jugadorRepository.save(jugador);
    }

    // Actualizar un jugador existente
    @Transactional
    public void updateJugador(Jugador jugador) {
        jugadorRepository.update(jugador);
    }

    // Eliminar un jugador por ID
    @Transactional
    public void deleteJugadorById(Long id) {
        jugadorRepository.deleteById(id);
    }

    // Obtener un jugador por ID (si necesitas esta función)
    public Jugador getJugadorById(Long id) {
        return jugadorRepository.findById(id);
    }
}
