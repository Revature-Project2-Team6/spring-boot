package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.gson.Gson;
import com.revature.data.UserRepository;
import com.revature.dto.Credentials;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    @InjectMocks
    private UserService uServ;

    private User dummyUser;

    @BeforeEach
    void setUp() throws Exception {
        this.dummyUser = new User(1, "auser", "Project2Team6!", "auser@dat.boi");
    }

    @AfterEach
    void tearDown() throws Exception {
        this.dummyUser = null;
    }

    /**
     * Clones a <code>User</code> object using <code>Gson</code> serialization and
     * deserialization.
     *
     * @param u the <code>User</code> instance to clone
     * @return a deep copy of <code>u</code>
     * @see <a href=
     *      "https://www.baeldung.com/java-deep-copy#2-json-serialization-with-gson">https://www.baeldung.com/java-deep-copy#2-json-serialization-with-gson</a>
     */
    private User cloneUser(User u) {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(u), User.class);
    }

    @Test
    void testAuthenticate_Success() {
        User expected = this.dummyUser;
        String username = expected.getUsername();
        String password = expected.getPassword();
        given(this.mockUserRepo.findUserByUsernameAndPassword(username, password)).willReturn(Optional.of(expected));

        User actual = this.uServ.authenticate(new Credentials(username, password));
        assertEquals(expected, actual);
        verify(this.mockUserRepo, times(1)).findUserByUsernameAndPassword(username, password);
    }

    @Test
    void testAuthenticate_Failure() {
        User expected = this.dummyUser;
        String username = expected.getUsername();
        String password = expected.getPassword();
        given(this.mockUserRepo.findUserByUsernameAndPassword(username, password)).willReturn(Optional.empty());

        Credentials creds = new Credentials(username, password);
        assertThrows(AuthenticationException.class, () -> this.uServ.authenticate(creds));
    }

    @Test
    void testAdd() {
        User expected = cloneUser(this.dummyUser);
        this.dummyUser.setId(0);
        given(this.mockUserRepo.save(this.dummyUser)).willReturn(expected);

        User actual = this.uServ.add(this.dummyUser);
        assertEquals(expected, actual);
        verify(this.mockUserRepo, times(1)).save(this.dummyUser);
    }

    @Test
    void testFindAll() {
        List<User> expected = new ArrayList<>();
        expected.add(this.dummyUser);
        expected.add(new User(2, "acarasimon96", "ChunChun~1", "timothyb@example.com"));
        expected.add(new User(3, "itshector", "$wappiBo1", "itshector@example.com"));
        given(this.mockUserRepo.findAll()).willReturn(expected);

        Set<User> actual = this.uServ.findAll();
        assertEquals(expected.size(), actual.size());
        verify(this.mockUserRepo, times(1)).findAll();
    }

    @Test
    void testGetByUsername() {
        String username = this.dummyUser.getUsername();
        given(this.mockUserRepo.findByUsername(username)).willReturn(Optional.of(this.dummyUser));

        User expected = this.dummyUser;
        User actual = this.uServ.getByUsername(username);
        assertEquals(expected, actual);
        assertEquals(username, actual.getUsername());
        verify(this.mockUserRepo, times(1)).findByUsername(username);
    }

    @Test
    void testGetByUsername_Failure_UnknownUsername() {
        String username = this.dummyUser.getUsername();
        given(this.mockUserRepo.findByUsername(username)).willReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> this.uServ.getByUsername(username));
        verify(this.mockUserRepo, times(1)).findByUsername(username);
    }

    @Test
    void testGetById_Success() {
        int id = this.dummyUser.getId();
        given(this.mockUserRepo.findById(id)).willReturn(Optional.of(this.dummyUser));

        User expected = this.dummyUser;
        User actual = this.uServ.getById(id);
        assertEquals(expected, actual);
        assertEquals(id, actual.getId());
        verify(this.mockUserRepo, times(1)).findById(id);
    }

    @Test
    void testGetById_Success_IdLessThanZero() {
        int id = -1;
        assertNull(this.uServ.getById(id));
        verify(this.mockUserRepo, never()).findById(id);
    }

    @Test
    void testGetById_Failure_UnknownId() {
        int id = this.dummyUser.getId();
        given(this.mockUserRepo.findById(id)).willReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> this.uServ.getById(id));
        verify(this.mockUserRepo, times(1)).findById(id);
    }

    @Test
    void testRemove() {
        int id = this.dummyUser.getId();
        willDoNothing().given(this.mockUserRepo).deleteById(id);

        this.uServ.remove(id);
        verify(this.mockUserRepo, times(1)).deleteById(id);
    }

    @Test
    void testUpdate() {
        User dummyUserOld = cloneUser(this.dummyUser);
        this.dummyUser.setPassword("someNewPa$$W0rd");
        given(this.mockUserRepo.save(this.dummyUser)).willReturn(this.dummyUser);

        User expected = this.dummyUser;
        User actual = this.uServ.update(this.dummyUser);

        assertEquals(expected, actual);
        assertNotEquals(dummyUserOld, actual);
        verify(this.mockUserRepo, times(1)).save(this.dummyUser);
    }

}
