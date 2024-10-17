package sv.edu.udb.dwfcatedra.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.NoArgsConstructor;
import sv.edu.udb.dwfcatedra.repository.EventoRepository;
import sv.edu.udb.dwfcatedra.repository.domain.Evento;

import java.util.List;

@Named
@RequestScoped
@NoArgsConstructor
public class EventoService {

    @Inject
    private EventoRepository eventoRepository;

    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    public Evento getEventoById(Long id) {
        return eventoRepository.findById(id);
    }

    public long getTotalEventosCount() {
        return eventoRepository.count();
    }

    public void saveEvento(Evento evento) {
        eventoRepository.save(evento);
    }

    public void deleteEventoById(Long eventoId) {
        Evento existingEvento = eventoRepository.findById(eventoId);
        if (existingEvento != null) {
            eventoRepository.delete(existingEvento);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento eliminado con éxito", null));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se encontró el evento a eliminar", null));
        }
    }

    public void updateEvento(Evento evento) {
        if (evento != null && evento.getId() != null) {
            Evento existingEvento = eventoRepository.findById(evento.getId());
            if (existingEvento != null) {
                existingEvento.setNombre(evento.getNombre());
                existingEvento.setDescripcion(evento.getDescripcion());
                existingEvento.setFecha(evento.getFecha());
                existingEvento.setLugar(evento.getLugar());
                existingEvento.setTipo(evento.getTipo());

                eventoRepository.update(existingEvento);
            }
        }
    }
}
