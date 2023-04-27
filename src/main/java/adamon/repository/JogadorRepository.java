package adamon.repository;

import adamon.model.Jogador;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {


}
