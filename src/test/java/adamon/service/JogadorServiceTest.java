package adamon.service;

import adamon.model.Adamon;
import adamon.model.Jogador;
import adamon.repository.JogadorRepository;
import adamon.util.TesteUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static adamon.util.TesteUtils.obterAdamon;
import static adamon.util.TesteUtils.obterJogador;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
 class JogadorServiceTest {
    @InjectMocks
    JogadorService jogadorService;

    @Mock
    private JogadorRepository repository;


    @Test
    void deveConseguirComprarAdamon() {
        Jogador jogador = obterJogador();
        Adamon adamon = obterAdamon();
        jogador.setSaldo(BigDecimal.valueOf(100));
        Mockito.when(repository.findById(jogador.getId())).thenReturn(Optional.of(jogador));

        jogadorService.comprarAdamon(jogador, adamon);

        assertFalse(jogador.getAdamons().isEmpty());
        Mockito.verify(repository).save(jogador);
    }

    @Test
    void naoDeveConseguirComprarAdamonNaoPossuiSaldo() {
        Jogador jogador = obterJogador();
        jogador.setSaldo(BigDecimal.valueOf(0)); //saldo 0
        Adamon adamon = obterAdamon();

        Assertions.assertThrows(RuntimeException.class, () -> {
            jogadorService.comprarAdamon(jogador, adamon);
        });
    }

    @Test
    void naoDeveConseguirComprarAdamonPoisEquipeEstaCheia() {
        Jogador jogador = obterJogador();
        jogador.setAdamons(TesteUtils.obterAdamons()); //equipe cheia
        Adamon adamon = obterAdamon();

        Assertions.assertThrows(RuntimeException.class, () -> {
            jogadorService.comprarAdamon(jogador, adamon);
        });
    }

    @Test
     void testTemAdamonsVivos() {
        List<Adamon> equipe = new ArrayList<>();
        Adamon adamon = new Adamon();
        adamon.setName("Quit");
        adamon.setVida(0);

        Adamon adamon2 = new Adamon();
        adamon2.setName("Leon");
        adamon2.setVida(0);

        equipe.add(adamon);
        equipe.add(adamon2);


        assertFalse(jogadorService.adamonVivo(equipe));
    }

    @Test
     void testEscolherAdamon() {
        List<Adamon> equipe = new ArrayList<>();
        Adamon adamon = new Adamon();
        adamon.setName("Fox");
        adamon.setVida(10);

        Adamon adamon2 = new Adamon();
        adamon2.setName("Zion");
        adamon2.setVida(0);

        equipe.add(adamon);
        equipe.add(adamon2);

        assertEquals(jogadorService.selecionarAdamon(equipe), equipe.get(0));
    }

    @Test
     void testBatalhar() {
        Jogador jogador1 = new Jogador();
        jogador1.setNickname("Schrilex");

        Jogador jogador2 = new Jogador();
        jogador2.setNickname("Goat");

        Adamon adamon1 = new Adamon();
        adamon1.setName("Marsh");
        adamon1.setVida(10);

        Adamon adamon2 = new Adamon();
        adamon2.setName("Roax");
        adamon2.setVida(0);

        Adamon adamon3 = new Adamon();
        adamon3.setName("Setup");
        adamon3.setVida(0);

        List<Adamon> equipe1 = new ArrayList<>();
        equipe1.add(adamon1);
        equipe1.add(adamon2);
        equipe1.add(adamon3);

        List<Adamon> equipe2 = new ArrayList<>();
        equipe2.add(adamon3);
        equipe2.add(adamon3);
        equipe2.add(adamon2);

        jogador1.setAdamons(equipe1);
        jogador2.setAdamons(equipe2);

        jogadorService.batalhar(jogador1, jogador2);

        assertTrue(!jogadorService.adamonVivo(jogador1.getAdamons()) || !jogadorService.adamonVivo(jogador2.getAdamons()));
    }
}
