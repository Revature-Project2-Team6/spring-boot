package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
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
import com.revature.data.SpeciesRepository;
import com.revature.exceptions.SpeciesNotFoundException;
import com.revature.models.Species;


@ExtendWith(MockitoExtension.class)
class SpeciesServiceTest {

    @Mock
    private SpeciesRepository mockSpeciesRepo;

    @InjectMocks
    private SpeciesService sServ;

    private Species dummySpecies;

    @BeforeEach
    void setUp() throws Exception {
        this.dummySpecies = new Species(1, "Dummy", "A dummy species");
    }

    @AfterEach
    void tearDown() throws Exception {
        this.dummySpecies = null;
    }

    /**
     * Clones a <code>Character</code> object using <code>Gson</code> serialization
     * and deserialization.
     *
     * @param c the <code>Character</code> instance to clone
     * @return a deep copy of <code>c</code>
     * @see <a href=
     *      "https://www.baeldung.com/java-deep-copy#2-json-serialization-with-gson">https://www.baeldung.com/java-deep-copy#2-json-serialization-with-gson</a>
     */
    private Species cloneSpecies(Species c) {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(c), Species.class);
    }

    @Test
    void testAdd() {
        Species expected = cloneSpecies(this.dummySpecies);
        given(this.mockSpeciesRepo.save(this.dummySpecies)).willReturn(expected);

        Species actual = this.sServ.add(this.dummySpecies);

        assertEquals(expected, actual);
        verify(this.mockSpeciesRepo, times(1)).save(this.dummySpecies);
    }

    @Test
    void testFindAll() {
        List<Species> expected = new ArrayList<>();
        expected.add(this.dummySpecies);
        expected.add(new Species(2, "human", "A human being"));
        expected.add(new Species(2, "Wookie", "The species of Chewbacca"));
        given(this.mockSpeciesRepo.findAll()).willReturn(expected);

        Set<Species> actual = this.sServ.findAll();
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual));
        verify(this.mockSpeciesRepo, times(1)).findAll();
    }

    @Test
    void testFindbyName_Success() {
        String name = this.dummySpecies.getSpeciesName();
        given(this.mockSpeciesRepo.findBySpeciesName(name)).willReturn(Optional.of(this.dummySpecies));

        Species expected = this.dummySpecies;
        Species actual = this.sServ.findbyName(name);

        assertEquals(expected, actual);
        assertEquals(name, actual.getSpeciesName());
        verify(this.mockSpeciesRepo, times(1)).findBySpeciesName(name);
    }

    @Test
    void testFindbyName_Success_EmptyStringParam() {
        String name = "";
        assertNull(this.sServ.findbyName(name));
        verify(this.mockSpeciesRepo, never()).findBySpeciesName(name);
    }

    @Test
    void testFindbyName_Failure_NameNotFound() {
        String name = this.dummySpecies.getSpeciesName();
        given(this.mockSpeciesRepo.findBySpeciesName(name)).willThrow(SpeciesNotFoundException.class);

        assertThrows(SpeciesNotFoundException.class, () -> this.sServ.findbyName(name));
        verify(this.mockSpeciesRepo, times(1)).findBySpeciesName(name);
    }

}
