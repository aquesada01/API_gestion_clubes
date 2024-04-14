package API.gestion_clubes.Services;

import API.gestion_clubes.Entities.ClubEntity;
import API.gestion_clubes.Repositories.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClubService {

    //Instanciación de ClubRepository
    @Autowired
    private ClubRepository clubRepo;

    /**
     * Busca un club por su nombre.
     *
     * @param nombre El nombre del club que se va a buscar.
     * @return ResponseEntity 200 cuyo body contiene el club buscado si el club existe.
     * @return ResponseEntity 404 si el club no existe.
     */
    public ResponseEntity<?> findByNombre(String nombre){
        ClubEntity club = clubRepo.findByNombre(nombre);
        if (club!= null) {
            return ResponseEntity.ok(club);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se ha encontrado un club con el nombre: " + nombre);
        }
    }

    /**
     * Crea un club en base de datos.
     *
     * @param club El club que se va a crear.
     * @return ResponseEntity 201 cuyo body contiene el club creado.
     * @exception Exception Si se presenta error al grabar el club.
     */
    public ResponseEntity<?> saveClub(ClubEntity club){
        try{
            club = clubRepo.save(club);
            return ResponseEntity.status(HttpStatus.CREATED).body(club);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Ha ocurrido un error al grabar el club. \nError: "+e.getMessage());
        }
    }

    /**
     * Actualiza un club por su id.
     *
     * @param club El club que se va a actualizar.
     * @return ResponseEntity 200 cuyo body contiene el club actualizado.
     * @exception Exception Si se presenta error al grabar el club.
     */
    public ResponseEntity<?> updateClub(ClubEntity club){
        try{
            club = clubRepo.save(club);
            return ResponseEntity.ok(club);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Ha ocurrido un error al grabar el club. \nError: "+e.getMessage());
        }
    }

    /**
     * Elimina un club por su id.
     *
     * @param id El club que se va a eliminar.
     * @return ResponseEntity 404 si no se encuentra un club por el id.
     * @return ResponseEntity 200 si encuentra el club y lo elimina satisfactoriamente.
     * @exception Exception Si se presenta error al eliminar el club.
     */
    public ResponseEntity<?> deleteById(Long id){
        Optional<ClubEntity> club=clubRepo.findById(id);
        try{
            if(club.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado un club con el id: "+id);
            }else{
                System.out.println(club);
                clubRepo.deleteById(id);
                return ResponseEntity.ok("Se ha eliminado satisfactoriamente el club con id: "+id);
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se ha presentado un error: \n" +e.getMessage());
        }
    }

    /**
     * Busca todos los clubes.
     *
     * @return ResponseEntity 200 cuyo body contiene la lista de clubes.
     * @exception Exception Si se presenta error al ejecutar la consulta.
     */
    public ResponseEntity<?> findAll(){
        try {
            return ResponseEntity
                    .ok(clubRepo.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Se ha presentado un error al obtener la lista de clubes. \nError:"+e.getMessage());
        }
    }
}
