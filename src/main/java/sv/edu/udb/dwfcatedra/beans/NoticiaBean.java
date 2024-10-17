package sv.edu.udb.dwfcatedra.beans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
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
import sv.edu.udb.dwfcatedra.repository.domain.Noticia;
import sv.edu.udb.dwfcatedra.service.NoticiaService;

@Named
@ViewScoped
public class NoticiaBean implements Serializable {

    @Inject
    private NoticiaService noticiaService;

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String titulo;

    @Getter @Setter
    private String descripcion;

    @Getter @Setter
    private String fecha;

    @Getter @Setter
    private Noticia noticia;

    @Getter
    private List<Noticia> noticias;

    @Getter @Setter
    private int currentPage = 0;

    @Getter @Setter
    private int rowsPerPage = 4;

    @Getter
    private int totalRows;

    @PostConstruct
    public void init() {
        if (this.id != null) {
            loadNoticia();
        }
        else{
            noticia = new Noticia();
        }
        loadNoticias();
    }

    public void loadNoticia() {
            noticia = noticiaService.getNoticiaById(this.id);
    }

    public void loadNoticias() {
        totalRows = (int) noticiaService.getTotalNoticiasCount();
        paginateNoticias();
    }

    public void paginateNoticias() {
        if (totalRows > 0) {
            int start = currentPage * rowsPerPage;
            int end = Math.min(start + rowsPerPage, totalRows);
            noticias = noticiaService.getAllNoticias().subList(start, end);
        } else {
            noticias = new ArrayList<>();
        }
    }
    public void nextPage() {
        if ((currentPage + 1) * rowsPerPage < totalRows) {
            currentPage++;
            paginateNoticias();
        }
    }

    public void prevPage() {
        if (currentPage > 0) {
            currentPage--;
            paginateNoticias();
        }
    }


    public String redirectUpdateNoticia(Long noticiaId) {
        this.id = noticiaId;  // Asigna el id de la noticia a editar
        return "editarNoticia.xhtml?faces-redirect=true&amp;id=" + this.id;
    }

    public String eliminarNoticia(Long noticiaId) {
        noticiaService.deleteNoticiaById(noticiaId);
        return "noticiasAdmin.xhtml?faces-redirect=true";
    }

    public String createNoticia() {
        Noticia nuevaNoticia = new Noticia();
        nuevaNoticia.setTitulo(this.noticia.getTitulo());
        nuevaNoticia.setDescripcion(this.noticia.getDescripcion());
        nuevaNoticia.setFecha(this.noticia.getFecha());

        noticiaService.saveNoticia(nuevaNoticia);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Noticia creada con éxito", null));

        return "noticiasAdmin.xhtml?faces-redirect=true";
    }

    public String update() {
        Noticia noticiaEditada = new Noticia();
        noticiaEditada.setId(this.id);
        noticiaEditada.setTitulo(this.noticia.getTitulo());
        noticiaEditada.setDescripcion(this.noticia.getDescripcion());
        noticiaEditada.setFecha(this.noticia.getFecha());

        noticiaService.updateNoticia(noticiaEditada);

        return "noticiasAdmin.xhtml?faces-redirect=true";
    }


    public String formatFecha(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(fecha);
    }

}



