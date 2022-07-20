package com.revature.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.data.CharacterRepository;
import com.revature.exceptions.CharacterNotFoundException;
import com.revature.models.Character;


@Service
public class CharacterService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private CharacterRepository charRepo;

    public CharacterService(CharacterRepository charRepo) {
        super();
        this.charRepo = charRepo;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Character addCharacter(Character c) {

        return this.charRepo.save(c);
    }

    @Transactional(readOnly = true)
    public Set<Character> findAll() {

        return this.charRepo.findAll().stream().collect(Collectors.toSet());
    }

    @Transactional(readOnly = true)
    public Set<Character> findByOwnerId(int id) {

        return this.charRepo.findByOwnerId(id).stream().collect(Collectors.toSet());
    }

    @Transactional(readOnly = true)
    public Character findByName(String name) {

        return this.charRepo.findByName(name).stream().findFirst().orElseThrow(CharacterNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<Character> findBySpeciesId(int id) {
        return this.charRepo.findBySpeciesId(id);
    }

    @Transactional(readOnly = true)
    public Character getById(int id) {

        if (id <= 0) {
            this.log.warn("Id must be greater than 0. The Id passed was: {}", id);
            return null;
        }
        return this.charRepo.findById(id).orElseThrow(CharacterNotFoundException::new);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void remove(int id) {
        this.charRepo.deleteById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Character update(Character c) {
        return this.charRepo.save(c);
    }

}
