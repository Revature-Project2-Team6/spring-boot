package com.revature.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.data.SpeciesRepository;
import com.revature.exceptions.SpeciesNotFoundException;
import com.revature.models.Species;

@Service
public class SpeciesService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private SpeciesRepository speciesRepo;
	
	public SpeciesService(SpeciesRepository speciesRepo) {
		super();
		this.speciesRepo = speciesRepo;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Species add(Species s) {
		
		Species returnedSpecies = new Species();
		
		try {
			
			returnedSpecies = speciesRepo.save(s);
		} catch (DataIntegrityViolationException e) {
			
		}
		
		return returnedSpecies;
	}
	
	@Transactional(readOnly = true)
	public Set<Species> findAll() {

		return speciesRepo.findAll().stream().collect(Collectors.toSet());
	}
	
	@Transactional(readOnly = true)
	public Species findbyName(String name) {

		if (name == "") {
			log.warn("Name can not be empty. Name passed: ", name);
			return null;
		} else {
			return speciesRepo.findBySpeciesName(name).orElseThrow(SpeciesNotFoundException::new);		}
	}

}
