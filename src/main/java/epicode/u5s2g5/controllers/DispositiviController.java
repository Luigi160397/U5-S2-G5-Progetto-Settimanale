package epicode.u5s2g5.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import epicode.u5s2g5.entities.Dispositivo;
import epicode.u5s2g5.entities.payloads.ModificaDispositivoPayload;
import epicode.u5s2g5.entities.payloads.NewDispositivoPayload;
import epicode.u5s2g5.exceptions.NotFoundException;
import epicode.u5s2g5.services.DispositiviService;

@RestController
@RequestMapping("/dispositivi")
public class DispositiviController {
	@Autowired
	DispositiviService dispositiviService;

	@GetMapping("")
	public Page<Dispositivo> getUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return dispositiviService.find(page, size, sortBy);
	}

	@GetMapping("/{id}")
	public Dispositivo getById(@PathVariable UUID id) {
		return dispositiviService.findById(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID id) throws NotFoundException {
		dispositiviService.findByIdAndDelete(id);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Dispositivo createDispositivo(@RequestBody @Validated NewDispositivoPayload body) {
		return dispositiviService.create(body);
	}

	@PutMapping("/{id}")
	public Dispositivo updateDispositivo(@PathVariable UUID id, @RequestBody ModificaDispositivoPayload body)
			throws Exception {
		return dispositiviService.findByIdAndUpdate(id, body);
	}

}
