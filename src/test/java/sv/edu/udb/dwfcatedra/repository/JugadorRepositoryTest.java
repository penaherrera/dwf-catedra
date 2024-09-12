package sv.edu.udb.dwfcatedra.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sv.edu.udb.dwfcatedra.repository.domain.Jugador;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class JugadorRepositoryTest {

    @InjectMocks
    private JugadorRepository jugadorRepository;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Jugador> typedQuery;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Jugador> jugadoresMock = Arrays.asList(new Jugador(), new Jugador());

        when(entityManager.createQuery("select j from Jugador j", Jugador.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(jugadoresMock);

        List<Jugador> result = jugadorRepository.findAll();

        assertEquals(jugadoresMock.size(), result.size());
    }
}
