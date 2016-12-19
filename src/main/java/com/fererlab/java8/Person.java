package com.fererlab.java8;

public class Person {

    private String name;
    private Integer age;
    private Boolean gender;

    public Person() {
    }

    public Person(String name, Integer age, Boolean gender) {
	this.name = name;
	this.age = age;
	this.gender = gender;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Integer getAge() {
	return age;
    }

    public void setAge(Integer age) {
	this.age = age;
    }

    public Boolean getGender() {
	return gender;
    }

    public void setGender(Boolean gender) {
	this.gender = gender;
    }

    @Override
    public String toString() {
	return "Person{" +
		"name='" + name + '\'' +
		", age=" + age +
		", gender=" + gender +
		'}';
    }
}
