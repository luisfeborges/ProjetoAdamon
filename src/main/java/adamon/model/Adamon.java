package adamon.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_adamon")
@Data
public class Adamon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer vida;
    @Column(nullable = false)
    private Integer ataque;
    @Column(nullable = false)
    private Integer defesa;

    @Column(nullable = false)
    private Integer inteligencia;

    @Column(nullable = false)
    private Integer poder;

    @Column(nullable = false)
    private Integer velocidade;

    @Column(nullable = false)
    private String urlFoto;

    @Column(nullable = false)
    private BigDecimal preco;


    public void atacar(Adamon adversario) {
        while (this.getVida() > 0 && adversario.getVida() > 0) {
            this.atacar(adversario);
            adversario.atacar(this);
        }

        if (this.getVida() <= 0 ){
            System.out.println(this.getName() + " perdeu a batalha!");
        }
        else {
            System.out.println(adversario.getName() + " perdeu a batalha!");
        }
    }
}
