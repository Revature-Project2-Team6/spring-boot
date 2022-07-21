package com.revature.web;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
