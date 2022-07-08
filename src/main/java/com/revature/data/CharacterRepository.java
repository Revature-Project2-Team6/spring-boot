package com.revature.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Character;

/**
 * The DAO layer class for </code>Character</code> model instances.
 *
 * @author Teejae Bautista
 */
public interface CharacterRepository extends JpaRepository<Character, Integer> {

    List<Character> findByOwnerId(int id);

    List<Character> findByName(String name);

    List<Character> findBySpeciesId(int id);

}
