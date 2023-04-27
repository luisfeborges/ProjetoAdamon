package adamon.model;

import lombok.Data;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "tb_jogador")
@Data
public class Jogador {
    @Id
    @Column(name = "id_jogador")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nickname;

    @Column
    private String password;

    @Column
    private BigDecimal saldo;

    @ManyToMany
    @JoinTable(name = "Jogador_Adamons",
            joinColumns = {@JoinColumn(name = "IdJogador")},
            inverseJoinColumns = {@JoinColumn(name = "IdAdamons")})
    private List<Adamon> adamons = new ArrayList<>();

    public Jogador() {
        saldo = new BigDecimal(100);
    }
}
