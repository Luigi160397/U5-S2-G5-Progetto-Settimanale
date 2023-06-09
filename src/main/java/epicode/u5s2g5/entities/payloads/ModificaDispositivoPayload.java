package epicode.u5s2g5.entities.payloads;

import java.util.UUID;

import epicode.u5s2g5.entities.TipoDispositivo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ModificaDispositivoPayload {
	@NotNull(message = "Il tipo del dispositivo è obbligatorio")
	TipoDispositivo tipoDispositivo;

	@NotNull(message = "Lo user-id del dispositivo è obbligatorio")
	UUID idUtente;
}
