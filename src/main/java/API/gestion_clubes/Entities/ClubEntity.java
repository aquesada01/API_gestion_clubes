package API.gestion_clubes.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "club")
public class ClubEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_club")
    private Long idClub;
    private String nombre;
    private String pais;
    private String ciudad;
    private String liga;
    private String estadio;
    @OneToMany(mappedBy = "club", cascade = CascadeType.DETACH)
    private List<JugadorEntity> jugadores;
}
