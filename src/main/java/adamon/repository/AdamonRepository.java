package adamon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import adamon.model.Adamon;
import org.springframework.stereotype.Repository;

@Repository
public interface AdamonRepository extends JpaRepository<Adamon, Long> {
}
