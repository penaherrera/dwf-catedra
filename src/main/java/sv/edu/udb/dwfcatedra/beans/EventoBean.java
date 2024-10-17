package sv.edu.udb.dwfcatedra.beans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import jakarta.annotation.PostConstruct;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sv.edu.udb.dwfcatedra.repository.domain.Evento;
import sv.edu.udb.dwfcatedra.service.EventoService;

@Named
@ViewScoped
public class EventosBean implements Serializable {

    @Inject
    private EventoService eventoService;

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String nombre;

    @Getter @Setter
    private String descripcion;

    @Getter @Setter
    private String lugar;

    @Getter @Setter
    private String tipo;

    @Getter @Setter
    private Date fecha;

    @Getter
    private Evento evento;

    @Getter
    private List<Evento> eventos;

    @Getter @Setter
    private int currentPage = 0; // Página actual

    @Getter @Setter
    private int rowsPerPage = 4;

    @Getter
    private int totalRows; // Total de filas (eventos)

    @PostConstruct
    public void init() {
        if (this.id != null) {
            loadEvento();
        } else {
            evento = new Evento();
        }
        loadEventos();
    }

    public void loadEvento() {
        evento = eventoService.getEventoById(this.id); // Carga el evento con el ID
    }

    public void loadEventos() {
        totalRows = (int) eventoService.getTotalEventosCount(); // Total de eventos
        paginateEventos(); // Paginación inicial, carga solo los necesarios
    }

    public void paginateEventos() {
        if (totalRows > 0) {
            int start = currentPage * rowsPerPage;
            int end = Math.min(start + rowsPerPage, totalRows);
            eventos = eventoService.getAllEventos().subList(start, end); // Carga solo los eventos paginados
        } else {
            eventos = new ArrayList<>(); // Asegúrate de que sea una lista vacía si no hay eventos
        }
    }

    public void nextPage() {
        if ((currentPage + 1) * rowsPerPage < totalRows) {
            currentPage++;
            paginateEventos(); // Actualiza la lista de eventos paginados
        }
    }

    public void prevPage() {
        if (currentPage > 0) {
            currentPage--;
            paginateEventos(); // Actualiza la lista de eventos paginados
        }
    }

    public String redirectUpdateEvento(Long eventoId) {
        this.id = eventoId;  // Asigna el id del evento a editar
        return "editarEvento.xhtml?faces-redirect=true&amp;id=" + this.id;  // Redirige a la página de edición con el ID
    }

    public String eliminarEvento(Long eventoId) {
        eventoService.deleteEventoById(eventoId);
        return "eventosAdmin.xhtml?faces-redirect=true";
    }

    public String createEvento() {
        Evento nuevoEvento = new Evento();
        nuevoEvento.setNombre(this.evento.getNombre());
        nuevoEvento.setDescripcion(this.evento.getDescripcion());
        nuevoEvento.setFecha(this.evento.getFecha());
        nuevoEvento.setLugar(this.evento.getLugar());
        nuevoEvento.setTipo(this.evento.getTipo());

        eventoService.saveEvento(nuevoEvento);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento creado con éxito", null));

        return "eventosAdmin.xhtml?faces-redirect=true";
    }

    public String update() {
        Evento eventoEditado = new Evento();
        eventoEditado.setId(this.id);
        eventoEditado.setNombre(this.evento.getNombre());
        eventoEditado.setDescripcion(this.evento.getDescripcion());
        eventoEditado.setFecha(this.evento.getFecha());
        eventoEditado.setLugar(this.evento.getLugar());
        eventoEditado.setTipo(this.evento.getTipo());

        eventoService.updateEvento(eventoEditado); // Actualiza el evento

        return "eventosAdmin.xhtml?faces-redirect=true"; // Redirige de vuelta a la lista de eventos
    }

    public String formatFecha(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(fecha);
    }
}
