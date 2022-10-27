package it.springsecurity.tools;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

public class SecurityTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		System.out.println(bcrypt.encode("1234"));
		Pbkdf2PasswordEncoder pbk2 =
				new Pbkdf2PasswordEncoder("secret", 30000, 256);
		System.out.println(pbk2.encode("1234"));
		SCryptPasswordEncoder scrypt = new SCryptPasswordEncoder();
		System.out.println(scrypt.encode("1234"));
		/*TextEncryptor enc = Encryptors.noOpText();
		System.out.println(enc.encrypt("prova"));*/
	}

}
