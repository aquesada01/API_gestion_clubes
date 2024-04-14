package API.gestion_clubes.Controllers;

import API.gestion_clubes.Entities.JugadorEntity;
import API.gestion_clubes.Services.JugadorRequest;
import API.gestion_clubes.Services.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jugador")
public class JugadorController {
    //Instanciación de JugadorService para el manejo de métodos
    @Autowired
    private JugadorService jugadorService;

    /**
     * Busca un club por su nombre.
     *
     * @param documento El NumeroDocumento del jugador que se va a buscar.
     * @return ResponseEntity 200 cuyo body contiene el jugador buscado si existe.
     * @return ResponseEntity 404 si el jugador no existe.
     */
    @GetMapping("/buscar")
    public ResponseEntity<?> findByNumeroDocumento(@RequestParam String documento){
        return jugadorService.findByNumeroDocumento(documento);
    }

    /**
     * Crea un jugador en base de datos.
     *
     * @param jugador El jugador que se va a crear.
     * @return ResponseEntity 201 cuyo body contiene el jugador creado.
     * @exception Exception Si se presenta error al grabar el jugador.
     */
    @PostMapping("/crear")
    public ResponseEntity<?> saveJugador(@RequestBody JugadorRequest jugador){
        return jugadorService.saveJugador(jugador);
    }

    /**
     * Actualiza un jugador.
     *
     * @param jugador El jugador que se va a actualizar.
     * @return ResponseEntity 200 cuyo body contiene el jugador actualizado.
     * @exception Exception Si se presenta error al grabar el jugador.
     */
    @PutMapping("/actualizar")
    public ResponseEntity<?> updateJugador(@RequestBody JugadorRequest jugador){
        return jugadorService.updateJugador(jugador);
    }

    /**
     * Elimina un jugador por su id.
     *
     * @param id El jugador que se va a eliminar.
     * @return ResponseEntity 404 si no se encuentra un jugador por el id.
     * @return ResponseEntity 200 si encuentra el jugador y lo elimina satisfactoriamente.
     * @exception Exception Si se presenta error al eliminar el jugador.
     */
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> deleteById(@RequestParam Long id){
        return jugadorService.deleteById(id);
    }

    /**
     * Busca todos los jugadores.
     *
     * @return ResponseEntity 200 cuyo body contiene la lista de jugadores.
     * @exception Exception Si se presenta error al ejecutar la consulta.
     */
    @GetMapping("/todos")
    public ResponseEntity<?> findAll(){
        return jugadorService.findAll();
    }
}
