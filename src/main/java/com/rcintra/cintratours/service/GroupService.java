package com.rcintra.cintratours.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rcintra.cintratours.model.Group;
import com.rcintra.cintratours.repository.GroupRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepository repository;
	
	public Group findByName(String name) {
		return repository.findByName(name);
	}
	
	public Group save(Group group) {
		return repository.save(group);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public Optional<Group> findById(Long id) {
		return repository.findById(id);
	}
	
	public List<Group> findAll() {
		return repository.findAll();
	}
}
