package sv.edu.udb.dwfcatedra.beans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import sv.edu.udb.dwfcatedra.repository.domain.Noticia;
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

    @Getter
    private List<Jugador> jugadores;

    @Getter @Setter
    private Tecnico tecnico;

    @Getter
    private List<Tecnico> tecnicos;


    @PostConstruct
    public void init() {
        if (this.id != null) {
            loadMiembro();
        }
        else{
            loadMiembros();
        }
    }

    public void loadMiembro(){
    }

    public void loadMiembros(){
        jugadores = jugadorService.getAllJugadores();
        tecnicos = tecnicoService.getAllTecnicos();
    }

    public String createJugador(){
        Jugador nuevoJugador = new Jugador();
        nuevoJugador.setNombre(this.nombre);
        nuevoJugador.setApodo(this.apodo);
        nuevoJugador.setEdad(this.edad);
        nuevoJugador.setPosicion(this.posicion);
        nuevoJugador.setDorsal(this.dorsal);

        jugadorService.saveJugador(nuevoJugador);

        return "plantillaAdmin.xhtml?faces-redirect=true";
    }

    public String createTecnico(){

        Tecnico nuevoTecnico = new Tecnico();
        nuevoTecnico.setNombre(this.nombre);
        nuevoTecnico.setApodo(this.apodo);
        nuevoTecnico.setEdad(this.edad);
        nuevoTecnico.setCargo(this.cargo);

        tecnicoService.saveTecnico(nuevoTecnico);

        return "plantillaAdmin.xhtml?faces-redirect=true";
    }

    public String updateMiembro(String tipo){
        if (Objects.equals(tipo, "jugador")){

            Jugador jugadorEditado = new Jugador();

            jugadorEditado.setNombre(this.nombre);
            jugadorEditado.setApodo(this.apodo);
            jugadorEditado.setEdad(this.edad);
            jugadorEditado.setPosicion(this.posicion);
            jugadorEditado.setDorsal(this.dorsal);

            jugadorService.updateJugador(jugadorEditado);

        }
        else{

            Tecnico tecnicoEditado = new Tecnico();

            tecnicoEditado.setNombre(this.nombre);
            tecnicoEditado.setApodo(this.apodo);
            tecnicoEditado.setEdad(this.edad);
            tecnicoEditado.setCargo(this.cargo);

            tecnicoService.updateTecnico(tecnicoEditado);

        }

        return "plantillaAdmin.xhtml?faces-redirect=true";
    }


    public String eliminarMiembro(Long miembroId, String tipo) {

        if (Objects.equals(tipo, "Jugador")){
            jugadorService.deleteJugadorById(miembroId);
        }
        else{
            tecnicoService.deleteTecnicoById(miembroId);
        }
        return "plantillaAdmin.xhtml?faces-redirect=true";
    }

}
