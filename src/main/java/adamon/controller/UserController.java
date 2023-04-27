package adamon.controller;

import adamon.model.Adamon;
import adamon.model.Jogador;
import adamon.service.AdamonService;
import adamon.service.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/jogador")
@RestController
public class UserController {

    @Autowired
    private JogadorService jogadorService;

    @Autowired
    private AdamonService adamonService;

    
    @PostMapping
    public Jogador createNewJogador(Jogador jogador){
        return jogadorService.criarNovoJogador(jogador);
    }
    
    @PutMapping("/comprar-adamon")
    public void buyAdamon(@RequestParam("idAdamon") Long idAdamon, @RequestParam("idJogador")Long idJogador) {
        Jogador jogador = jogadorService.finById(idJogador);
        Adamon adamon = adamonService.finById(idAdamon);
        jogadorService.comprarAdamon(jogador, adamon);
    }
}
