package epicode.u5s2g5.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import epicode.u5s2g5.entities.Dispositivo;
import epicode.u5s2g5.entities.User;
import epicode.u5s2g5.entities.payloads.UserRegistrationPayload;
import epicode.u5s2g5.exceptions.NotFoundException;
import epicode.u5s2g5.services.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	UsersService usersService;

	@GetMapping("")
	public Page<User> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy) {
		return usersService.find(page, size, sortBy);
	}

	@GetMapping("/{id}")
	public User getById(@PathVariable UUID id) {
		return usersService.findById(id);
	}

	@GetMapping("/{id}/dispositivi")
	public List<Dispositivo> findDispositiviUtente(@PathVariable UUID id) {
		return usersService.findDispositiviUtente(id);
	}

	@PutMapping("/{id}")
	public User updateUser(@PathVariable UUID id, @RequestBody UserRegistrationPayload body) throws Exception {
		return usersService.findByIdAndUpdate(id, body);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID id) throws NotFoundException {
		usersService.findByIdAndDelete(id);
	}

}
