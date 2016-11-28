package com.fererlab.mem;


import java.util.Arrays;
import java.util.List;

public class User {

    private String name;
    private String city;
    private Integer age;
    private List<Group> groups;

    public User(String name, String city, Integer age, Group... groups) {
        this.name = name;
        this.city = city;
        this.age = age;
        this.groups = Arrays.asList(groups);
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public Integer getAge() {
        return age;
    }

    public List<Group> getGroups() {
        return groups;
    }

}
