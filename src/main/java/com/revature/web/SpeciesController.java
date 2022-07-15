package com.revature.web;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Species;
import com.revature.service.SpeciesService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/species")
public class SpeciesController {
	
	private SpeciesService sServ;
	
	public SpeciesController(SpeciesService sServ) {
		this.sServ = sServ;
	}
	
	@PostMapping
	public ResponseEntity<Species> registerSpecies(@Valid @RequestBody Species s) {
		System.out.println("In the registerSPecies");
		return ResponseEntity.ok(this.sServ.add(s));
	}
	
	@GetMapping
	public ResponseEntity<Set<Species>> getAll() {
		return ResponseEntity.ok(this.sServ.findAll());
	}
	
	@GetMapping("/{name}")
    public ResponseEntity<Species> findSpeciesByName(@PathVariable("name") String name) {
        try {
            return ResponseEntity.ok(this.sServ.findbyName(name));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
