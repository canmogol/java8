package com.fererlab.liskov;

import java.util.List;

public class UserService {

    public void addDefaultUsers(List<String> users) {
	users.add("admin");
	users.add("root");
	users.add("system");
    }

}
