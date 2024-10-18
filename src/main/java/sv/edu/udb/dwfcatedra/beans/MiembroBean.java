package sv.edu.udb.dwfcatedra.beans;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import sv.edu.udb.dwfcatedra.repository.domain.Tecnico;
import sv.edu.udb.dwfcatedra.repository.domain.Jugador;
import sv.edu.udb.dwfcatedra.service.TecnicoService;
import sv.edu.udb.dwfcatedra.service.JugadorService;

@Named
@ViewScoped
public class MiembroBean implements Serializable{

    @Inject
    private JugadorService jugadorService;

    @Inject
    private TecnicoService tecnicoService;

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String nombre;

    @Getter @Setter
    private String apodo;

    @Getter @Setter
    private int edad;

    @Getter @Setter
    private String posicion;

    @Getter @Setter
    private int dorsal;

    @Getter @Setter
    private String cargo;

    @Getter @Setter
    private Jugador jugador;

    @Getter @Setter
    private Tecnico tecnico;

    @Getter @Setter
    private List<Jugador> jugadores;

    @Getter @Setter
    private List<Tecnico> tecnicos;

    @Getter @Setter
    private String tipoMiembro;

    // Nuevas propiedades para paginación específica de jugadores
    @Getter @Setter
    private int currentPageJugadores = 0;

    @Getter @Setter
    private int rowsPerPageJugadores = 5;

    @Getter
    private int totalRowsJugadores;

    @Getter @Setter
    private int currentPageTecnicos = 0;

    @Getter @Setter
    private int rowsPerPageTecnicos = 5;

    @Getter
    private int totalRowsTecnicos;

    @PostConstruct
    public void init() {
        if (this.id != null || this.tipoMiembro != null) {
            loadMiembro();
        } else {
            loadMiembros();
        }
    }


    public void loadMiembro() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
        this.id = Long.parseLong(params.get("id"));
        this.tipoMiembro = params.get("tipo");

        System.out.println("getTipoMiembro(): " + getTipoMiembro());

        if (Objects.equals(this.tipoMiembro, "jugador")) {
            jugador = jugadorService.getJugadorById(this.id);
            System.out.println("Cargando jugador: " + jugador);
            System.out.println("this.jugador: " + this.jugador);
        } else {
            System.out.println("Cargando técnico con ID: " + this.id);
            tecnico = tecnicoService.getTecnicoById(this.id);
        }
    }




    public void loadMiembros() {
        // Cargar todos los jugadores y técnicos
        jugadores = jugadorService.getAllJugadores();
        tecnicos = tecnicoService.getAllTecnicos();


    }

    // Métodos de paginación para jugadores
    public void paginateJugadores() {
        if (totalRowsJugadores > 0) {
            int start = currentPageJugadores * rowsPerPageJugadores;
            int end = Math.min(start + rowsPerPageJugadores, totalRowsJugadores);
            jugadores = jugadorService.getAllJugadores().subList(start, end);
        } else {
            jugadores = new ArrayList<>();
        }
    }

    public void nextPageJugadores() {
        if ((currentPageJugadores + 1) * rowsPerPageJugadores < totalRowsJugadores) {
            currentPageJugadores++;
            paginateJugadores();
        }
    }

    public void prevPageJugadores() {
        if (currentPageJugadores > 0) {
            currentPageJugadores--;
            paginateJugadores();
        }
    }

    // Métodos de paginación para técnicos
    public void paginateTecnicos() {
        if (totalRowsTecnicos > 0) {
            int start = currentPageTecnicos * rowsPerPageTecnicos;
            int end = Math.min(start + rowsPerPageTecnicos, totalRowsTecnicos);
            tecnicos = tecnicoService.getAllTecnicos().subList(start, end);
        } else {
            tecnicos = new ArrayList<>();
        }
    }

    public void nextPageTecnicos() {
        if ((currentPageTecnicos + 1) * rowsPerPageTecnicos < totalRowsTecnicos) {
            currentPageTecnicos++;
            paginateTecnicos();
        }
    }

    public void prevPageTecnicos() {
        if (currentPageTecnicos > 0) {
            currentPageTecnicos--;
            paginateTecnicos();
        }
    }

    public String createJugador() {
        Jugador nuevoJugador = new Jugador();
        nuevoJugador.setNombre(this.nombre);
        nuevoJugador.setApodo(this.apodo);
        nuevoJugador.setEdad(this.edad);
        nuevoJugador.setPosicion(this.posicion);
        nuevoJugador.setDorsal(this.dorsal);

        jugadorService.saveJugador(nuevoJugador);

        return "jugadoresAdmin.xhtml?faces-redirect=true";
    }

    public String createTecnico() {
        Tecnico nuevoTecnico = new Tecnico();
        nuevoTecnico.setNombre(this.nombre);
        nuevoTecnico.setApodo(this.apodo);
        nuevoTecnico.setEdad(this.edad);
        nuevoTecnico.setCargo(this.cargo);

        tecnicoService.saveTecnico(nuevoTecnico);

        return "tecnicosAdmin.xhtml?faces-redirect=true";
    }

    public String redirectUpdateMiembro(Long miembroId, String tipo) {
        this.setId(miembroId);
        this.setTipoMiembro(tipo);

        System.out.println("en redirect getTipoMiembro(): " + getTipoMiembro());

        if (Objects.equals(tipo, "jugador")) {
            return "editarJugador.xhtml?faces-redirect=true&amp;id=" + this.id + "&amp;tipo=" + this.tipoMiembro;
        } else {
            return "editarTecnico.xhtml?faces-redirect=true&amp;id=" + this.id + "&amp;tipo=" + this.tipoMiembro;
        }
    }


    public String updateMiembro(String tipo) {
        if (Objects.equals(tipo, "jugador")) {
            Jugador jugadorEditado = jugadorService.getJugadorById(this.id);
            if (jugadorEditado != null) {
                jugadorEditado.setNombre(this.nombre);
                jugadorEditado.setApodo(this.apodo);
                jugadorEditado.setEdad(this.edad);
                jugadorEditado.setPosicion(this.posicion);
                jugadorEditado.setDorsal(this.dorsal);
                jugadorService.updateJugador(jugadorEditado);
            }
        } else {
            Tecnico tecnicoEditado = tecnicoService.getTecnicoById(this.id);
            if (tecnicoEditado != null) {
                tecnicoEditado.setNombre(this.nombre);
                tecnicoEditado.setApodo(this.apodo);
                tecnicoEditado.setEdad(this.edad);
                tecnicoEditado.setCargo(this.cargo);
                tecnicoService.updateTecnico(tecnicoEditado);
            }
        }
        return "jugadoresAdmin.xhtml?faces-redirect=true";
    }



    public String eliminarMiembro(Long miembroId, String tipo) {

        if (Objects.equals(tipo, "jugador")){
            jugadorService.deleteJugadorById(miembroId);
            return "jugadoresAdmin.xhtml?faces-redirect=true";
        }
        else{
            tecnicoService.deleteTecnicoById(miembroId);
            return "tecnicosAdmin.xhtml?faces-redirect=true";
        }
    }
}
