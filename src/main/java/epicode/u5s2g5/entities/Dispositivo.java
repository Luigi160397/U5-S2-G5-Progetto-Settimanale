package epicode.u5s2g5.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "dispositivi")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Dispositivo {
	@Id
	@GeneratedValue
	private UUID id;
	@Enumerated(EnumType.STRING)
	private TipoDispositivo tipo;
	@Enumerated(EnumType.STRING)
	private StatoDispositivo statoDispositivo;
	@ManyToOne
	@JsonBackReference
	private User user;

	public Dispositivo(TipoDispositivo tipo, StatoDispositivo statoDispositivo, User user) {
		super();
		this.tipo = tipo;
		this.statoDispositivo = statoDispositivo;
		this.user = user;
	}

	public Dispositivo(TipoDispositivo tipo) {
		super();
		this.tipo = tipo;
		this.statoDispositivo = StatoDispositivo.DISPONIBILE;

	}

}
