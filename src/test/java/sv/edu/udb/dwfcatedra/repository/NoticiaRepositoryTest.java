package sv.edu.udb.dwfcatedra.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import sv.edu.udb.dwfcatedra.repository.domain.Noticia;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class NoticiaRepositoryTest {

    @InjectMocks
    private NoticiaRepository noticiaRepository;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Noticia> typedQuery;

    @BeforeEach
    public void setUp() {
        // Inicializa los mocks (Objetos que simulan comportamiento) antes de cada prueba
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Noticia> noticiasMock = Arrays.asList(new Noticia(), new Noticia());

        when(entityManager.createQuery(Mockito.anyString(), Mockito.eq(Noticia.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(noticiasMock);

        List<Noticia> result = noticiaRepository.findAll();

        assertEquals(noticiasMock.size(), result.size());
    }
}
