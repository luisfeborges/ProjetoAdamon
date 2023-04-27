package adamon.dto;

import adamon.model.Jogador;

public class UserDtoResponse {

    public static Jogador Dtoconverte(UserDTO dto){
        Jogador jogador = new Jogador();
        jogador.setNickname(dto.getNickName());
        jogador.setPassword(dto.getPassword());
        return jogador;
    }
}
