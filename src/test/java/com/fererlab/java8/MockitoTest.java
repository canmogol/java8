package com.fererlab.java8;

import com.fererlab.java8.enterprise.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class MockitoTest {

    private final Integer USER_ID = 1;
    private final String USER_USERNAME = "john";

    private final Integer NOT_EXISTING_USER_ID = 999;
    private final String NOT_EXISTING_USER_USERNAME = "lucifer";
    private
    @Mock
    UserDAO userDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findUsernameTest() {
        UserService userService = new UserServiceImpl();
        UserDAO userDao = new UserDAOStaticImpl();
        userService.setDao(userDao);
        String username = userService.findUsername(USER_ID);
        assertEquals(username, USER_USERNAME);
    }

    @Test
    public void findUsernameMockitoTest() {
        // mocking
        // here the "findById" method call with parameter "NOT_EXISTING_USER_ID" is the key
        Mockito.when(userDAO.findById(NOT_EXISTING_USER_ID)).
                thenReturn(new UserEntity(NOT_EXISTING_USER_ID, NOT_EXISTING_USER_USERNAME));

        // test body
        UserService userService = new UserServiceImpl();
        userService.setDao(userDAO);
        String username = userService.findUsername(NOT_EXISTING_USER_ID);
        Mockito.verify(userDAO).findById(NOT_EXISTING_USER_ID);
        assertEquals(username, NOT_EXISTING_USER_USERNAME);
    }

}
