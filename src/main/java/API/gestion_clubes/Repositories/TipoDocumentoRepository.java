package API.gestion_clubes.Repositories;

import API.gestion_clubes.Entities.TipoDocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoEntity,Long> {
    TipoDocumentoEntity findByAbreviacion(String abreviacion);
}
