package com.example.test404;

import com.example.test404.models.Person;
import com.example.test404.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Test404Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Test404Application.class, args);
	}

	@Autowired
	private PersonRepository personRepository;

	@Override
	public void run(String... args) throws Exception {
		//personRepository.save(new Person("Liza", "Got", "lissssaGot@mail.ru", "8223241414446"));
	}

}
