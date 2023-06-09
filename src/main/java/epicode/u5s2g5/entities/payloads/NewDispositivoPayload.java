package epicode.u5s2g5.entities.payloads;

import epicode.u5s2g5.entities.TipoDispositivo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class NewDispositivoPayload {
	@NotNull(message = "Il tipo del dispositivo è obbligatorio")

	TipoDispositivo tipoDispositivo;

}
