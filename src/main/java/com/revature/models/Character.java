package com.revature.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The model class representing a character in the game.
 *
 * @author Teejae Bautista
 */
@Entity
@Table(name = "characters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Length(min = 2)
    @Column(nullable = false)
    private String name;

    // private Species species;

    @URL
    private String imageUrl;

    // private Stats stats;

    // private List<Skill> skills;

    @NotBlank
    @Column(nullable = false)
    private User owner;

    public Character(String name, String imageUrl) {
        this.name = name;
        // this.species = species;
        this.imageUrl = imageUrl;
    }

}
