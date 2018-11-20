package com.rcintra.cintratours;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rcintra.cintratours.model.Event;
import com.rcintra.cintratours.model.Group;
import com.rcintra.cintratours.repository.GroupRepository;

@Component
public class Initializer implements CommandLineRunner {
	
	private final GroupRepository repository;
	
	public Initializer(GroupRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... args) throws Exception {
		Stream.of("São Paulo", "Rio", 
				"Minas Gerais", "Parana")
				.forEach(name -> repository.save(new Group(name)));
						
		Group g = repository.findByName("Rio");
		
		Event e = Event.builder().title("Full Stack Reactive")
				.description("Reactive with Spring Boot + React")
				.date(Instant.parse("2018-11-11T18:00:00.000Z"))
				.build();
		
		g.setEvents(Collections.singleton(e));
		repository.save(g);
		
		repository.findAll().forEach(System.out::println);
	}

}
