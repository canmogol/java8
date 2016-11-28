package com.fererlab.mem;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private Map<String, List<Double>> map = new HashMap<>();

    private List<String> cities = new ArrayList<String>() {{
        add("New York");
        add("London");
        add("Tokyo");
        add("Sydney");
        add("Paris");
    }};
    private List<Group> groups;

    public static void main(String[] args) {
        Main main = new Main();
        main.map.put("q", new LinkedList<>());
        main.map.put("qm", new LinkedList<>());

        main.generate();
        main.queryMap();
        main.query();
        main.queryMap();
        main.query();
        main.queryMap();
        main.query();
        main.queryMap();
        main.query();
        main.queryMap();
        main.query();
        main.map.keySet().stream().forEach(key -> {
            System.out.println(key + ":" + main.map.get(key).stream().mapToDouble(d -> d).average());
        });
    }

    private void generate() {
        groups = IntStream.range(0, 100_000)
                .mapToObj(i -> new Group(UUID.randomUUID().toString().substring(0, 5), new LinkedList<>()))
                .map(group -> {
                    IntStream.range(0, 10).forEach(i -> {
                        User user = new User(UUID.randomUUID().toString().substring(0, 10), cities.get(new Random().nextInt(cities.size())), new Random().nextInt(100), group);
                        group.getUsers().add(user);
                    });
                    return group;
                })
                .collect(Collectors.toList());
    }

    private void queryMap() {
        // query groups which users with name begins with "a", from "New York" and adult
        long time = System.nanoTime();
        Set<Group> queryGroups = groups.stream()
                .flatMap(group -> group.getUsers().stream())
                .filter(user -> user.getName().startsWith("a"))
                .filter(user -> cities.get(0).equals(user.getCity()))
                .filter(user -> user.getAge() > 18)
                .flatMap(user -> user.getGroups().stream())
                .collect(Collectors.toSet());
        double groupQuery = (System.nanoTime() - time) / 1_000_000_000.0;
        map.get("qm").add(groupQuery);
        System.out.println("m #queryGroups= " + queryGroups.size());
    }

    private void query() {
        // query groups which users with name begins with "a", from "New York" and adult
        long time = System.nanoTime();
        List<User> users = new LinkedList<>();
        for (Group group : groups) {
            users.addAll(group.getUsers());
        }

        List<User> usersWithA = new LinkedList<>();
        for (User user : users) {
            if (user.getName().startsWith("a")) {
                usersWithA.add(user);
            }
        }

        List<User> usersFromNewYork = new LinkedList<>();
        for (User user : usersWithA) {
            if (cities.get(0).equals(user.getCity())) {
                usersFromNewYork.add(user);
            }
        }

        List<User> usersAdult = new LinkedList<>();
        for (User user : usersFromNewYork) {
            if (user.getAge() > 18) {
                usersAdult.add(user);
            }
        }

        Set<Group> groupList = new HashSet<>();
        for (User user : usersAdult) {
            groupList.addAll(user.getGroups());
        }

        double groupQuery = (System.nanoTime() - time) / 1_000_000_000.0;
        map.get("q").add(groupQuery);
        System.out.println("q #groupList= " + groupList.size());
    }


}
