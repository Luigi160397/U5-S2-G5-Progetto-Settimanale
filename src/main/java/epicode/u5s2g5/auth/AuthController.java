package epicode.u5s2g5.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epicode.u5s2g5.auth.payloads.AuthenticationSuccessfullPayload;
import epicode.u5s2g5.entities.User;
import epicode.u5s2g5.entities.payloads.UserLoginPayload;
import epicode.u5s2g5.entities.payloads.UserRegistrationPayload;
import epicode.u5s2g5.exceptions.NotFoundException;
import epicode.u5s2g5.exceptions.UnauthorizedException;
import epicode.u5s2g5.services.UsersService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UsersService usersService;

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody @Validated UserRegistrationPayload body) {
		User createdUser = usersService.create(body);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UserLoginPayload body)
			throws NotFoundException {

		User user = usersService.findByUsername(body.getUsername());

		if (!body.getPassword().matches(user.getPassword()))
			throw new UnauthorizedException("Credenziali non valide");

		String token = JWTTools.createToken(user);

		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
	}

}