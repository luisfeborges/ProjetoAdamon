package adamon.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Inventario {

    private  Jogador jogador;
    public List<Adamon> adamons;

    public Inventario() {
        adamons = new ArrayList<>();
    }
}
