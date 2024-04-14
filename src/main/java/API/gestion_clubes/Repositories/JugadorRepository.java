package API.gestion_clubes.Repositories;

import API.gestion_clubes.Entities.JugadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends JpaRepository<JugadorEntity,Long> {
    JugadorEntity findByNumeroDocumento(String documento);
}
