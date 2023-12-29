package com.example.myshop;

import com.example.myshop.models.Address;
import com.example.myshop.models.User;
import com.example.myshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class MyShopApplication {
	private final UserRepository userRepository;
	private final PasswordEncoder encoder;


	public static void main(String[] args) {
		SpringApplication.run(MyShopApplication.class, args);
	}

	//@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {

			User user = User.builder()
					.firstName("teo5")
					.lastName("ti5")
					.email("teo9@gmail.com")
					.password(encoder.encode("123"))
					.addresses(List.of(new Address("abc", "bcd", "def", "zzz")))
					.role("ROLE_USER")
					.build();
			userRepository.save(user);
			System.out.println(user.getId());

		};
	}

}
