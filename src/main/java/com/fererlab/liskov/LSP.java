package com.fererlab.liskov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LSP {

    public static void main(String[] args) {

       /* List<String> users = new ArrayList<>();
	users.add("jack");
        users.add("john");

        UserService userService = new UserService();
        userService.addDefaultUsers(users);

        System.out.println("ADD USERS TEST = " + users);


        List<String> users2 = somewhereWeDontKnowAbout();

        userService.addDefaultUsers(users2);
        System.out.println("users2 = " + users2);*/

    }

    private static List<String> somewhereWeDontKnowAbout() {
	List<String> users2 = new ArrayList<>();

	users2.add("tamaguci");
	users2.add("asimo");

	users2 = Collections.unmodifiableList(users2);

	return users2;
    }

}
