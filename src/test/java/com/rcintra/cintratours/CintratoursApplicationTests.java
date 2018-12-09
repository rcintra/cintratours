package com.rcintra.cintratours;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.rcintra.cintratours.model.Group;
import com.rcintra.cintratours.repository.GroupRepository;
import com.rcintra.cintratours.service.GroupService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CintratoursApplicationTests {

	@Autowired
	private GroupService service;
	
	@MockBean
	private GroupRepository repository;
	
	@MockBean
	private Initializer init;
	
	@Test
	public void getGroupsTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new Group(999l, "Group 2", "End1", "Sao Paulo")).collect(Collectors.toList()));
		assertEquals(1, service.findAll().size());
	}
	
	@Test
	public void getGroupByIdTest() {
		Long id = 999l;
		Group group = new Group(id, "Group 2", "End1", "Sao Paulo");
		when(repository.findById(id)).thenReturn(Stream
				.of(group).findAny());
		assertEquals(group, service.findById(id).get());
	}
	
	@Test
	public void saveGroupTest() {
		Group group = new Group(999l, "Group 2", "End1", "Sao Paulo");
		when(repository.save(group)).thenReturn(group);
		assertEquals(group, service.save(group));
	}
	
	@Test
	public void contextLoads() {
	}

}
