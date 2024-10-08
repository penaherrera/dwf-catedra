package sv.edu.udb.dwfcatedra.beans;

import lombok.Getter;
import lombok.Setter;
import sv.edu.udb.dwfcatedra.repository.NoticiaRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import sv.edu.udb.dwfcatedra.repository.domain.Noticia;

@Named
@RequestScoped
public class NoticiaBean implements Serializable {

    @Inject
    private NoticiaRepository noticiaRepository;

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private Noticia noticia;

    @Getter
    private List<Noticia> noticias;

    @PostConstruct
    public void init() {
        loadNoticias();
    }

    // Cargar una noticia por id
    public void loadNoticia() {
        if (id != null) {
            noticia = noticiaRepository.findById(id);
        }
    }

    // Cargar todas las noticias
    public void loadNoticias() {
        noticias = noticiaRepository.findAll();
    }
}
