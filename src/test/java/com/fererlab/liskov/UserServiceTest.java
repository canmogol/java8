package com.fererlab.liskov;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    @Test
    public void testAddDefaultUsers() throws Exception {

        UserService userService = new UserService();

        List<String> users = new ArrayList<>();

        userService.addDefaultUsers(users);

        System.out.println("users = " + users);

        Assert.assertTrue(!users.isEmpty());

    }
}