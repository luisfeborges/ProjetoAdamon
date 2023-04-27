package adamon.service;

import adamon.model.Adamon;
import adamon.repository.AdamonRepository;
import adamon.util.TesteUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdamonServiceTest {
    @InjectMocks
    AdamonService adamonService;

    @Mock
    AdamonRepository repository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    void buscarAdamonCorretamente() {
        when(repository.findAll()).thenReturn(TesteUtils.obterAdamons());

        List<Adamon> adamons = adamonService.findAllAdamons();

        Assertions.assertFalse(adamons.isEmpty());
    }

    @Test
    void buscarAdamonCorretamenteListaVazia() {
        when(repository.findAll()).thenReturn(new ArrayList<>());

        List<Adamon> adamons = adamonService.findAllAdamons();

        assertTrue(adamons.isEmpty());
    }

    @Test
    void salvarNovoAdamonCorretamente() {
        //cenário
        Adamon adamonASerSalvo = new Adamon();
        adamonASerSalvo.setName("Carlos");

        Adamon adamonSalvo = new Adamon();
        adamonSalvo.setId(1L);

        when(repository.save(Mockito.any(Adamon.class))).thenReturn(adamonSalvo);

        Adamon adamon = adamonService.createNewAdamon(adamonASerSalvo);

        Assertions.assertNotNull(adamon);
        Assertions.assertEquals(adamonSalvo.getId(), adamon.getId());
    }
}
