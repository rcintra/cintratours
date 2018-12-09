package com.rcintra.cintratours.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rcintra.cintratours.model.Group;
import com.rcintra.cintratours.service.GroupService;

@RestController
@RequestMapping("/api")
public class GroupController {
	
	private final Logger log = LoggerFactory.getLogger(GroupController.class);
	
	@Autowired
	private GroupService service;

	@GetMapping("/groups")
	Collection<Group> groups() {
		return service.findAll();
	}
	
	@GetMapping("/group/{id}")
	ResponseEntity<?> getGroup(@PathVariable Long id) {
		Optional<Group> group = service.findById(id);
		return group.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/group")
	ResponseEntity<Group> createGroup(@Valid @RequestBody Group group) throws URISyntaxException {
		log.info("Request to create group: {}", group);
		Group result = service.save(group);
		return ResponseEntity.created(new URI("/api/group/" + result.getId()))
				.body(result);
	}
	
	@PutMapping("/group/{id}")
	ResponseEntity<Group> updateGroup(@PathVariable Long id, @Valid @RequestBody Group group) {
		group.setId(id);
		log.info("Request to update group: {}", group);
		Group result = service.save(group);
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping("/group/{id}")
	public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
		log.info("Request to delete group: {}", id);
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
