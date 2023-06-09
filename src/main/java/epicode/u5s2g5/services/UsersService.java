package epicode.u5s2g5.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import epicode.u5s2g5.entities.Dispositivo;
import epicode.u5s2g5.entities.StatoDispositivo;
import epicode.u5s2g5.entities.User;
import epicode.u5s2g5.entities.payloads.UserRegistrationPayload;
import epicode.u5s2g5.exceptions.BadRequestException;
import epicode.u5s2g5.exceptions.NotFoundException;
import epicode.u5s2g5.repositories.UsersRepository;

@Service
public class UsersService {
	@Autowired
	UsersRepository usersRepo;

	public Page<User> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return usersRepo.findAll(pageable);
	}

	public User create(UserRegistrationPayload u) {

		usersRepo.findByEmail(u.getEmail()).ifPresent(user -> {
			throw new BadRequestException("Email " + user.getEmail() + " già in uso!");
		});
		List<Dispositivo> dispositivi = new ArrayList<>();
		User newUser = new User(u.getUsername(), u.getNome(), u.getCognome(), u.getEmail(), u.getPassword(),
				dispositivi);
		return usersRepo.save(newUser);
	}

	public User findById(UUID id) throws NotFoundException {
		return usersRepo.findById(id).orElseThrow(() -> new NotFoundException("Utente non trovato!"));
	}

	public User findByIdAndUpdate(UUID id, UserRegistrationPayload u) throws NotFoundException {
		User found = this.findById(id);

		found.setId(id);
		found.setUsername(u.getUsername());
		found.setNome(u.getNome());
		found.setCognome(u.getCognome());
		found.setEmail(u.getEmail());
		found.setPassword(u.getPassword());
		found.setDispositivi(found.getDispositivi());

		return usersRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		User found = this.findById(id);
		List<Dispositivo> dispositivi = found.getDispositivi();
		Iterator<Dispositivo> i = dispositivi.iterator();
		while (i.hasNext()) {
			Dispositivo current = i.next();
			current.setStatoDispositivo(StatoDispositivo.DISPONIBILE);
			current.setUser(null);

		}
		usersRepo.delete(found);
	}

	public User findByUsername(String username) throws NotFoundException {
		return usersRepo.findByUsername(username)
				.orElseThrow(() -> new NotFoundException("Nessun utente con username: " + username + " trovato"));
	}

	public List<Dispositivo> findDispositiviUtente(UUID id) {
		User found = this.findById(id);

		return found.getDispositivi();
	}

}
