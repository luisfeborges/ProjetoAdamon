package adamon.service;

import adamon.model.Adamon;
import adamon.model.Inventario;
import adamon.model.Jogador;
import adamon.util.TesteUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class InventarioServiceTest {

    @Test
    void deveDpositarAdamonNoInventario(){
        Jogador jogador = new Jogador();
        jogador.setAdamons(TesteUtils.obterAdamons());
        InventarioService inventarioService = new InventarioService();

        Inventario inventario = new Inventario();
        inventario.setJogador(jogador);

        inventarioService.depositarAdamon(inventario, jogador.getAdamons().get(0));

        Assertions.assertTrue(jogador.getAdamons().size() < TesteUtils.obterAdamons().size());
    }

    @Test
    void deveConseguirRecuperarAdamon(){
        Jogador jogador = new Jogador();
        jogador.setAdamons(Collections.singletonList(TesteUtils.obterAdamon()));

        Inventario inventario = new Inventario();
        inventario.setAdamons(Collections.singletonList(new Adamon()));
        inventario.setJogador(jogador);

        InventarioService inventarioService = new InventarioService();
        inventarioService.recuperarAdamon(inventario, inventario.getAdamons().get(0));

        Assertions.assertTrue(jogador.getAdamons().size() > 1);
    }
}
