package com.revature.web;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revature.models.Character;
import com.revature.service.CharacterService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/characters")
public class CharacterController {

    private CharacterService cserv;

    @Autowired
    public CharacterController(CharacterService cserv) {
        super();
        this.cserv = cserv;
    }

    // Get all the characters(likely wont use other than showing the full DB in the
    // demo)
    @GetMapping
    public ResponseEntity<Set<Character>> getAllCharacters() {
        return ResponseEntity.ok(this.cserv.findAll());
    }

    // Get the character by the ID
    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacter(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.cserv.getById(id));
    }

    // get the characters the User owns by the User id
    @GetMapping("/find/{id}")
    public ResponseEntity<Set<Character>> findByUser(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.cserv.findByOwnerId(id));
    }

    // add a new character to the DB
    @PostMapping("/add")
    public ResponseEntity<Character> addCharacter(@Valid @RequestBody Character c) {
        // get the body from the browser
        return ResponseEntity.ok(this.cserv.addCharacter(c));
    }

    // update the character
    @PutMapping("/update")
    public ResponseEntity<Character> updateCharacter(@Valid @RequestBody Character c) {
        return ResponseEntity.ok(this.cserv.update(c));
    }

}
