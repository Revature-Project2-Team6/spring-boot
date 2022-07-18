package com.revature.web;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.service.UserService;

/**
 * Controller class for <code>User</code>-related actions.
 *
 * @author Teejae Bautista
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/users")
public class UserController {

    private UserService uServ;

    // Note that declaring @Autowired here is not needed since Spring already
    // detects this constructor
    public UserController(UserService uServ) {
        this.uServ = uServ;
    }

    /**
     * Registers a user into the database.
     *
     * @param u The <code>User</code> object to register
     * @return A <code>ResponseEntity</code> object indicating whether or not the
     *         registration was successful
     */
    @PostMapping("/add")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User u) {
        return ResponseEntity.ok(this.uServ.add(u));
    }

    /**
     * Updates the information of a <code>User</code> stored in the database.
     *
     * @param The <code>User</code> object to modify
     * @return A <code>ResponseEntity</code> object indicating whether or not the
     *         <code>User</code> was updated successfully
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @Valid @RequestBody User u) {
        try {
            this.uServ.getById(id);
            return ResponseEntity.ok(this.uServ.update(u));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves all users registered in the database.
     *
     * @return A <code>ResponseEntity</code> object containing a <code>Set</code> of
     *         users found in the database
     */
    @GetMapping("/")
    public ResponseEntity<Set<User>> getAll() {
        return ResponseEntity.ok(this.uServ.findAll());
    }

    /**
     * Fetches a specific user based on their ID.
     *
     * @param id The ID number of the <code>User</code> to get
     * @return A <code>ResponseEntity</code> object
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(this.uServ.getById(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
