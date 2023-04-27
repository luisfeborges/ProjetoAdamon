package adamon.service;

import adamon.model.Adamon;
import adamon.model.Inventario;
import adamon.model.Jogador;

import java.util.ArrayList;
import java.util.List;

public class InventarioService {

    public void depositarAdamon(Inventario inventario, Adamon adamon){
        Jogador jogador = inventario.getJogador();
        List<Adamon> adamons = jogador.getAdamons();

        boolean possuiAdamon = adamons.contains(adamon);
        boolean Maiorequipe = adamons.size() > 1;

        if(possuiAdamon && Maiorequipe){
            inventario.getAdamons().add(adamon);
            List<Adamon> adamonsJogador = new ArrayList<>(jogador.getAdamons());
            adamonsJogador.remove(adamon);
            jogador.setAdamons(adamonsJogador);
        }
    }

    public void recuperarAdamon(Inventario inventario, Adamon adamon){
        Jogador jogador = inventario.getJogador();

        boolean tamanhoEquipe = jogador.getAdamons().size() < 6 ;

        if(tamanhoEquipe){
            List<Adamon> adamonsInventario = new ArrayList<>(inventario.getAdamons());
            adamonsInventario.remove(adamon);

            List<Adamon> equipeJogador = new ArrayList<>(jogador.getAdamons());
            equipeJogador.add(adamon);
            jogador.setAdamons(equipeJogador);
        }
    }
}
