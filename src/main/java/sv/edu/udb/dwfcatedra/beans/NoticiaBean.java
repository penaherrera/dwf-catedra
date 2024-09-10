package sv.edu.udb.dwfcatedra.beans;

import lombok.Getter;
import sv.edu.udb.dwfcatedra.repository.NoticiaRepository;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import sv.edu.udb.dwfcatedra.repository.domain.Noticia;

@Named
@RequestScoped
public class NoticiaBean implements Serializable {

    @Inject
    private NoticiaRepository noticiaRepository;

    @Getter
    private List<Noticia> noticias;

    @PostConstruct
    public void init() {
        loadNoticias();
    }

    public void loadNoticias() {
        noticias = noticiaRepository.findAll();
    }
}
