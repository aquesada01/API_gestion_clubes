package API.gestion_clubes.Services;

import API.gestion_clubes.Entities.ClubEntity;
import API.gestion_clubes.Entities.TipoDocumentoEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JugadorRequest {
    private Long idJugador;
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String numeroDocumento;
    private String nacionalidad;
    private String perfilPierna;
    private String posicion;
    private String dorsal;
    private String club;
}
