package adamon.service;

import adamon.model.Adamon;
import adamon.model.Jogador;
import adamon.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class JogadorService {
    @Autowired
    private JogadorRepository jogadorRepository;

    public boolean adamonVivo(List<Adamon> equipe){
        for (Adamon adamons : equipe) {
            if (adamons.getVida() > 0) {
                return true;
            }
        }
        return false;
    }

    public Adamon selecionarAdamon(List<Adamon> equipe) {
        Adamon adamonSelecionado = null;
        for (Adamon adamon: equipe ) {
            if (adamon.getVida() > 0) {
                if(adamonSelecionado == null || adamon.getVida() < adamonSelecionado.getVida()) {
                    adamonSelecionado = adamon;
                }
            }
        }
        return adamonSelecionado;
    }

    public void batalhar(Jogador jogador, Jogador adversario){
        List<Adamon> equipeDoUser = jogador.getAdamons();
        List<Adamon> equipeAdversaria = adversario.getAdamons();

        while(this.adamonVivo(equipeDoUser) && this.adamonVivo(equipeAdversaria)){
            Adamon adamonJogador1 = selecionarAdamon(equipeDoUser);
            Adamon adamonJogador2 = selecionarAdamon(equipeAdversaria);

            adamonJogador1.atacar(adamonJogador2);
            adamonJogador2.atacar(adamonJogador1);
        }

        if (this.adamonVivo(equipeDoUser)){
            System.out.println(jogador.getNickname() + "Venceu a Batalha!");
        }else {
            System.out.println(adversario.getNickname() + "Venceu a Batalha!");
        }
    }


    public void comprarAdamon(Jogador jogador, Adamon adamon) {
        List<Adamon> equipeAdamonJogador = jogador.getAdamons();
        BigDecimal saldoAtual = jogador.getSaldo();
        BigDecimal valorAdamon = adamon.getPreco();

        boolean validacaoSaldo = saldoAtual.compareTo(valorAdamon) > 0;
        boolean validacaoEspacoEquipe = equipeAdamonJogador.size() < 6;

        if (validacaoEspacoEquipe && validacaoSaldo) {
            equipeAdamonJogador.add(adamon);
            jogador.setSaldo(saldoAtual.subtract(valorAdamon));
            atualizarJogador(jogador, jogador.getId());
        } else if (!validacaoSaldo) {
            throw new RuntimeException("Não possui saldo suficiente para compra!");
        } else if (!validacaoEspacoEquipe) {
            throw new RuntimeException("A equipe não possui espaço disponível");
        }
    }


    public Jogador finById(Long id){
        return jogadorRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Não encontramos o jogador com ID: " + id));
    }

    public Jogador criarNovoJogador(Jogador jogador){
        return jogadorRepository.save(jogador);
    }

    public void atualizarJogador(Jogador jogador, Long idJogador){
        finById(idJogador);
        jogador.setId(idJogador);
        jogadorRepository.save(jogador);
    }
}
