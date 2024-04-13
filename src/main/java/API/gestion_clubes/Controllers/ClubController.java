package API.gestion_clubes.Controllers;

import API.gestion_clubes.Entities.ClubEntity;
import API.gestion_clubes.Services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/club")
public class ClubController {
    //Instanciación de ClubService para el manejo de los métodos
    @Autowired
    private ClubService clubService;

    /**
     * Busca un club por su nombre.
     *
     * @param nombre El nombre del club que se va a buscar.
     * @return ResponseEntity 200 cuyo body contiene el club buscado si el club existe.
     * @return ResponseEntity 404 si el club no existe.
     */
    @GetMapping("/nombre")
    public ResponseEntity<?> findByNombre(@RequestParam String nombre){
        return clubService.findByNombre(nombre);
    }

    /**
     * Crea un club en base de datos.
     *
     * @param club El club que se va a crear.
     * @return ResponseEntity 201 cuyo body contiene el club creado.
     * @exception Exception Si se presenta error al grabar el club.
     */
    @PostMapping("/crear")
    public ResponseEntity<?> saveClub(@RequestBody ClubEntity club){
        return clubService.saveClub(club);
    }

    /**
     * Actualiza un club por su id.
     *
     * @param club El club que se va a actualizar.
     * @return ResponseEntity 200 cuyo body contiene el club actualizado.
     * @exception Exception Si se presenta error al grabar el club.
     */
    @PutMapping("/actualizar")
    public ResponseEntity<?> updateClub(@RequestBody ClubEntity club){
        return clubService.updateClub(club);
    }

    /**
     * Elimina un club por su id.
     *
     * @param id El club que se va a eliminar.
     * @return ResponseEntity 404 si no se encuentra un club por el id.
     * @return ResponseEntity 200 si encuentra el club y lo elimina satisfactoriamente.
     * @exception Exception Si se presenta error al eliminar el club.
     */
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> deleteById(@RequestParam Long id){
        return clubService.deleteById(id);
    }
}
