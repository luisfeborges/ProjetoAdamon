package adamon.util;

import adamon.model.Adamon;
import adamon.model.Jogador;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class TesteUtils {

    private static List<Jogador> obterJogadores(){

    Jogador jogador = new Jogador();
    Jogador jogador1 = new Jogador();
    Jogador jogador2 = new Jogador();
    Jogador jogador3 = new Jogador();

    return Arrays.asList(jogador, jogador1, jogador2, jogador3);
    }

    public static List<Adamon> obterAdamons(){
        Adamon adamon = new Adamon();
        Adamon adamon1 = new Adamon();
        Adamon adamon2 = new Adamon();
        Adamon adamon3 = new Adamon();
        Adamon adamon4 = new Adamon();
        Adamon adamon5 = new Adamon();


        return Arrays.asList(adamon, adamon1, adamon2, adamon3, adamon4, adamon5);
    }

    public static Adamon obterAdamon(){
        Adamon adamon = new Adamon();

        adamon.setDefesa(50);
        adamon.setAtaque(33);
        adamon.setPreco(new BigDecimal("1250"));
        adamon.setVida(100);
        adamon.setInteligencia(60);
        adamon.setPoder(85);
        adamon.setVelocidade(50);

        return adamon;
    }

    public static Jogador obterJogador(){
        Jogador jogador = new Jogador();
        jogador.setId(1L);
        jogador.setNickname("Guto459");
        jogador.setPassword("Guto459");

        return jogador;
    }

    public static String jsonAsString(Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível converter o objeto");
        }
    }
}
