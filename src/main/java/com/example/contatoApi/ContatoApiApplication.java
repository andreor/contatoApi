package com.example.contatoApi;

import com.example.contatoApi.model.Contato;
import com.example.contatoApi.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication

@Configuration
public class ContatoApiApplication implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(ContatoApiApplication.class, args);
	}

	@Autowired
	ContatoRepository contatoRepository;


	@Override
	public void run(String... args) throws Exception {
		Contato c1 = new Contato("1", "john1@domain.com","c1","v1","o1");
		Contato c2 = new Contato("2", "john2@domain.com","c2","v2","o2");
		Contato c3 = new Contato("3", "john3@domain.com","c3","v3","o3");
		Contato c4 = new Contato("4", "john4@domain.com","c4","v4","o4");

		contatoRepository.save(c1);
		contatoRepository.save(c2);
		contatoRepository.save(c3);
		contatoRepository.save(c4);
	}
}
