package com.revature.service;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.data.UserRepository;
import com.revature.models.User;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    @InjectMocks
    private UserService uServ;

    private User dummyUser;

    @BeforeEach
    void setUp() throws Exception {}

    @AfterEach
    void tearDown() throws Exception {
        this.dummyUser = null;
    }

    @Test
    void testAuthenticate_Success() {
        fail("Not yet implemented");
    }

    @Test
    void testAuthenticate_Failure_WrongPassword() {
        fail("Not yet implemented");
    }

    @Test
    void testAuthenticate_Failure_UnknownUsername() {
        fail("Not yet implemented");
    }

    @Test
    void testAdd_Success() {
        fail("Not yet implemented");
    }

    @Test
    void testAdd_Failure_UserExists() {
        fail("Not yet implemented");
    }

    @Test
    void testFindAll() {
        fail("Not yet implemented");
    }

    @Test
    void testGetByUsername_Success() {
        fail("Not yet implemented");
    }

    @Test
    void testGetByUsername_Failure_UnknownUsername() {
        fail("Not yet implemented");
    }

    @Test
    void testGetById_Success() {
        fail("Not yet implemented");
    }

    @Test
    void testGetById_Failure_UnknownId() {
        fail("Not yet implemented");
    }

    @Test
    void testRemove_Success() {
        fail("Not yet implemented");
    }

    @Test
    void testRemove_Failure_UnknownUser() {
        fail("Not yet implemented");
    }

    @Test
    void testUpdate_Success() {
        fail("Not yet implemented");
    }

    @Test
    void testUpdate_Failure_UnknownUser() {
        fail("Not yet implemented");
    }

}
