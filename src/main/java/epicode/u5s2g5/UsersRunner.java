package epicode.u5s2g5;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import epicode.u5s2g5.entities.Dispositivo;
import epicode.u5s2g5.entities.User;
import epicode.u5s2g5.repositories.UsersRepository;

@Component
public class UsersRunner implements CommandLineRunner {
	@Autowired
	UsersRepository usersRepo;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));

		List<User> usersDB = usersRepo.findAll();

		if (usersDB.size() == 0) {
			for (int i = 0; i < 10; i++) {
				try {
					String username = faker.name().username();
					String nome = faker.name().firstName();
					String cognome = faker.name().lastName();

					String email = faker.internet().emailAddress();
					String psw = faker.internet().password();

					List<Dispositivo> listaVuota = new ArrayList<>();
					User user = new User(username, nome, cognome, email, psw, listaVuota);
					usersRepo.save(user);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}

	}

}
