package com.fererlab.mem;

import java.util.List;

public class Group {

    private String name;
    private List<User> users;

    public Group(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }
}
