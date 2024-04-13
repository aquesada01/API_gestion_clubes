package API.gestion_clubes.Repositories;

import API.gestion_clubes.Entities.ClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<ClubEntity,Long> {
    //Método para obtener un ClubEntity por su nombre
    ClubEntity findByNombre(String nombre);
}
