package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.gson.Gson;
import com.revature.data.CharacterRepository;
import com.revature.models.Character;
import com.revature.models.User;


@ExtendWith(MockitoExtension.class)
class CharacterServiceTest {

    @Mock
    private CharacterRepository mockCharRepo;

    @InjectMocks
    private CharacterService cServ;
    private Character dummyCharacter;
    private User dummyUser;

    @BeforeEach
    void setUp() throws Exception {
        this.dummyUser = new User(1, "auser", "Project2Team6!", "auser@dat.boi");
        this.dummyCharacter = new Character(1, "Chewbacca", null, null, null, this.dummyUser);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.dummyUser = null;
        this.dummyCharacter = null;
    }

    /**
     * Clones a <code>Character</code> object using <code>Gson</code> serialization and
     * deserialization.
     *
     * @param c the <code>Character</code> instance to clone
     * @return a deep copy of <code>c</code>
     * @see <a href=
     *      "https://www.baeldung.com/java-deep-copy#2-json-serialization-with-gson">https://www.baeldung.com/java-deep-copy#2-json-serialization-with-gson</a>
     */
    private Character cloneCharacter(Character c) {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(c), Character.class);
    }

    @Test
    void testAddCharacter() {
        Character expected = cloneCharacter(this.dummyCharacter);
        given(this.mockCharRepo.save(this.dummyCharacter)).willReturn(expected);

        Character actual = this.cServ.addCharacter(this.dummyCharacter);
        assertEquals(expected, actual);
        verify(this.mockCharRepo, times(1)).save(this.dummyCharacter);
    }

    @Test
    void testFindAll() {
        User user2 = new User("asdf", "AW33kPa$$W0rd", "asdf@asdf.com");
        List<Character> expected = new ArrayList<>();
        expected.add(this.dummyCharacter);
        expected.add(new Character(2, "Not Han Solo", null, null, null, this.dummyUser));
        expected.add(new Character(3, "Jabba", null, null, null, user2));
        given(this.mockCharRepo.findAll()).willReturn(expected);

        Set<Character> actual = this.cServ.findAll();
        assertEquals(expected.size(), actual.size());
        verify(this.mockCharRepo, times(1)).findAll();

        // GC the 2nd dummy user
        user2 = null;
    }

    @Test
    @Disabled("Not yet implemented")
    void testFindByOwnerId_Success() {
        fail("Not yet implemented");
    }

    @Test
    @Disabled("Not yet implemented")
    void testFindByOwnerId_Success_NoCharacters() {
        fail("Not yet implemented");
    }

    @Test
    @Disabled("Not yet implemented")
    void testFindByOwnerId_Failure_UnknownId() {
        fail("Not yet implemented");
    }

    @Test
    @Disabled("Not yet implemented")
    void testFindByName_Success() {
        fail("Not yet implemented");
    }

    @Test
    @Disabled("Not yet implemented")
    void testFindByName_Success_Multiple() {
        fail("Not yet implemented");
    }

    @Test
    @Disabled("Not yet implemented")
    void testFindByName_Failure_UnknownName() {
        fail("Not yet implemented");
    }

    @Test
    @Disabled("Not yet implemented")
    void testFindBySpeciesId_Success() {
        fail("Not yet implemented");
    }

    @Test
    @Disabled("Not yet implemented")
    void testFindBySpeciesId_Failure_UnknownSpeciesId() {
        fail("Not yet implemented");
    }

    @Test
    void testGetById_Success() {
        int id = this.dummyCharacter.getId();
        given(this.mockCharRepo.findById(id)).willReturn(Optional.of(this.dummyCharacter));

        Character expected = this.dummyCharacter;
        Character actual = this.cServ.getById(id);
        assertEquals(expected, actual);
        assertEquals(id, actual.getId());
        verify(this.mockCharRepo, times(1)).findById(id);
    }

    @Test
    @Disabled("Not yet implemented")
    void testGetById_Failure_UnknownId() {
        fail("Not yet implemented");
    }

    @Test
    void testRemove() {
        int id = this.dummyCharacter.getId();
        willDoNothing().given(this.mockCharRepo).deleteById(id);

        this.cServ.remove(id);
        verify(this.mockCharRepo, times(1)).deleteById(id);
    }

    @Test
    void testUpdate() {
        Character dummyCharacterOld = cloneCharacter(this.dummyCharacter);
        this.dummyCharacter.setName("New Name");
        given(this.mockCharRepo.save(this.dummyCharacter)).willReturn(this.dummyCharacter);

        Character expected = this.dummyCharacter;
        Character actual = this.cServ.update(this.dummyCharacter);

        assertEquals(expected, actual);
        assertNotEquals(dummyCharacterOld, actual);
        verify(this.mockCharRepo, times(1)).save(this.dummyCharacter);
    }

}
