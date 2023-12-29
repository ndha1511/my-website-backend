package com.example.myshop;

import com.example.myshop.models.Address;
import com.example.myshop.models.Role;
import com.example.myshop.models.User;
import com.example.myshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class MyShopApplication {
	private final UserRepository userRepository;


	public static void main(String[] args) {
		SpringApplication.run(MyShopApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			User user = User.builder()
					.firstName("teo5")
					.lastName("ti5")
					.email("teo5@gmail.com")
					.password("123")
					.addresses(List.of(new Address("abc", "bcd", "def", "zzz")))
					.roles(List.of(Role.USER))
					.build();
			userRepository.save(user);
			System.out.println(user.getId());

		};
	}

}
