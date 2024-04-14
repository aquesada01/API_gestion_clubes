package API.gestion_clubes.Services;

import API.gestion_clubes.Entities.ClubEntity;
import API.gestion_clubes.Entities.JugadorEntity;
import API.gestion_clubes.Repositories.ClubRepository;
import API.gestion_clubes.Repositories.JugadorRepository;
import API.gestion_clubes.Repositories.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorService {
    //Instanciación de JugadorRepository para el manejo de métodos con JPA
    @Autowired
    private JugadorRepository jugadorRepo;
    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepo;
    @Autowired
    private ClubRepository clubRepo;

    /**
     * Busca un club por su nombre.
     *
     * @param documento El NumeroDocumento del jugador que se va a buscar.
     * @return ResponseEntity 200 cuyo body contiene el jugador buscado si existe.
     * @return ResponseEntity 404 si el jugador no existe.
     */
    public ResponseEntity<?> findByNumeroDocumento(String documento){
        JugadorEntity jugador = jugadorRepo.findByNumeroDocumento(documento);
        if (jugador!= null) {
            return ResponseEntity
                    .ok(jugador);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se ha encontrado el jugador con documento: " + documento);
        }
    }

    /**
     * Crea un jugador en base de datos.
     *
     * @param jugador El jugador que se va a crear.
     * @return ResponseEntity 201 cuyo body contiene el jugador creado.
     * @exception Exception Si se presenta error al grabar el jugador.
     */
    public ResponseEntity<?> saveJugador(JugadorRequest jugador){
        try{
            JugadorEntity jugadorEntity=new JugadorEntity();
            jugadorEntity.setNombre(jugador.getNombre());
            jugadorEntity.setApellido(jugador.getApellido());
            jugadorEntity.setTipoDocumento(tipoDocumentoRepo.findByAbreviacion(jugador.getTipoDocumento()));
            jugadorEntity.setNumeroDocumento(jugador.getNumeroDocumento());
            jugadorEntity.setNacionalidad(jugador.getNacionalidad());
            jugadorEntity.setPerfilPierna(jugador.getPerfilPierna());
            jugadorEntity.setPosicion(jugador.getPosicion());
            jugadorEntity.setDorsal(jugador.getDorsal());
            jugadorEntity.setClub(clubRepo.findByNombre(jugador.getClub()));
            jugadorRepo.save(jugadorEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(jugadorEntity);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Ha ocurrido un error al grabar el jugador. \nError: "+e.getMessage());
        }
    }

    /**
     * Actualiza un jugador.
     *
     * @param jugador El jugador que se va a actualizar.
     * @return ResponseEntity 200 cuyo body contiene el jugador actualizado.
     * @exception Exception Si se presenta error al grabar el jugador.
     */
    public ResponseEntity<?> updateJugador(JugadorRequest jugador){
        try{
            Optional<JugadorEntity>jugadorAux=jugadorRepo.findById(jugador.getIdJugador());
            if(jugadorAux.isPresent()){
                JugadorEntity jugadorEntity=new JugadorEntity();
                jugadorEntity.setIdJugador(jugador.getIdJugador());
                jugadorEntity.setNombre(jugador.getNombre());
                jugadorEntity.setApellido(jugador.getApellido());
                jugadorEntity.setTipoDocumento(tipoDocumentoRepo.findByAbreviacion(jugador.getTipoDocumento()));
                jugadorEntity.setNumeroDocumento(jugador.getNumeroDocumento());
                jugadorEntity.setNacionalidad(jugador.getNacionalidad());
                jugadorEntity.setPerfilPierna(jugador.getPerfilPierna());
                jugadorEntity.setPosicion(jugador.getPosicion());
                jugadorEntity.setDorsal(jugador.getDorsal());
                jugadorEntity.setClub(clubRepo.findByNombre(jugador.getClub()));
                jugadorRepo.save(jugadorEntity);
                return ResponseEntity.ok(jugadorEntity);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el jugador: "+jugador.getNombre());
            }
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Ha ocurrido un error al grabar el jugador. \nError: "+e.getMessage());
        }
    }

    /**
     * Elimina un jugador por su id.
     *
     * @param id El jugador que se va a eliminar.
     * @return ResponseEntity 404 si no se encuentra un jugador por el id.
     * @return ResponseEntity 200 si encuentra el jugador y lo elimina satisfactoriamente.
     * @exception Exception Si se presenta error al eliminar el jugador.
     */
    public ResponseEntity<?> deleteById(Long id){
        Optional<JugadorEntity> jugador=jugadorRepo.findById(id);
        try{
            if(jugador.isPresent()){
                jugadorRepo.deleteById(id);
                return ResponseEntity.ok("Se ha eliminado satisfactoriamente el jugador con id: "+id);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado un jugador con el id: "+id);
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se ha presentado un error: \n" +e.getMessage());
        }
    }

    /**
     * Busca todos los jugadores.
     *
     * @return ResponseEntity 200 cuyo body contiene la lista de jugadores.
     * @exception Exception Si se presenta error al ejecutar la consulta.
     */
    public ResponseEntity<?> findAll(){
        try{
            return ResponseEntity.ok(jugadorRepo.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se ha presentado un error al consultar los jugadores. \nError:"+e.getMessage());
        }
    }

}
