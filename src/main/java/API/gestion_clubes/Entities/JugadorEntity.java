package API.gestion_clubes.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "jugador")
public class JugadorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jugador")
    private Long idJugador;
    private String nombre;
    private String apellido;
    @ManyToOne
    @JoinColumn(name="tipo_documento", nullable = false)
    private TipoDocumentoEntity tipoDocumento;
    @Column(name = "numero_documento")
    private String numeroDocumento;
    private String nacionalidad;
    @Column(name = "perfil_pierna")
    private String perfilPierna;
    private String posicion;
    private String dorsal;
    @ManyToOne
    @JoinColumn(name = "club", nullable = true)
    @JsonIgnoreProperties("jugadores")
    private ClubEntity club;
}
