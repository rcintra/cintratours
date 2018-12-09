package com.rcintra.cintratours;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rcintra.cintratours.model.Event;
import com.rcintra.cintratours.model.Group;
import com.rcintra.cintratours.service.GroupService;

@Component
public class Initializer implements CommandLineRunner {
	
	/*private final GroupRepository repository;
	
	public Initializer(GroupRepository repository) {
		this.repository = repository;
	}*/
	
	@Autowired
	private GroupService service;

	@Override
	public void run(String... args) throws Exception {
		Stream.of("São Paulo", "Rio", 
				"Minas Gerais", "Parana")
				.forEach(name -> service.save(new Group(name)));
						
		Group g = service.findByName("Rio");
		
		Event e = Event.builder().title("Full Stack Reactive")
				.description("Reactive with Spring Boot + React")
				.date(Instant.parse("2018-11-11T18:00:00.000Z"))
				.build();
		
		g.setEvents(Collections.singleton(e));
		service.save(g);
		
		service.findAll().forEach(System.out::println);
	}

}
