package epicode.u5s2g5.entities.payloads;

import lombok.Getter;

@Getter
public class UserLoginPayload {
	String username;
	String password;
}