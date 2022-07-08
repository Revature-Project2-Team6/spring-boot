package com.revature.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.*;

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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@EqualsAndHashCode(exclude = { "owner" })
@ToString(exclude = { "owner" })
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

    @NotNull
    @ManyToOne
    private User owner;

    public Character(String name, String imageUrl) {
        this.name = name;
        // this.species = species;
        this.imageUrl = imageUrl;
    }

}
