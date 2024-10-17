package sv.edu.udb.dwfcatedra.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.NoArgsConstructor;
import sv.edu.udb.dwfcatedra.repository.NoticiaRepository;
import sv.edu.udb.dwfcatedra.repository.domain.Noticia;

import java.util.List;

@Named
@RequestScoped
@NoArgsConstructor
public class NoticiaService {

    @Inject
    private NoticiaRepository noticiaRepository;

    public List<Noticia> getAllNoticias(){
        return noticiaRepository.findAll();
    }

    public Noticia getNoticiaById(Long id){
        return noticiaRepository.findById(id);
    }

    public long getTotalNoticiasCount() {
        return noticiaRepository.count();
    }

    public void saveNoticia(Noticia noticia){
        noticiaRepository.save(noticia);
    }

    public void deleteNoticiaById(Long noticiaId) {
        Noticia existingNoticia = noticiaRepository.findById(noticiaId);
        if (existingNoticia != null) {
            noticiaRepository.delete(existingNoticia);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Noticia eliminada con éxito", null));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se encontró la noticia a eliminar", null));
        }
    }


    public void updateNoticia(Noticia noticia) {
        if (noticia != null && noticia.getId() != null) {
            Noticia existingNoticia = noticiaRepository.findById(noticia.getId());
            if (existingNoticia != null) {
                existingNoticia.setTitulo(noticia.getTitulo());
                existingNoticia.setDescripcion(noticia.getDescripcion());
                existingNoticia.setFecha(noticia.getFecha());

                noticiaRepository.update(existingNoticia);
            }
        }
    }

}
