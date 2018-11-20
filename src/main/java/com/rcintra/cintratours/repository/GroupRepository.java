package com.rcintra.cintratours.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rcintra.cintratours.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
	Group findByName(String name);
}
