package com.fererlab.java8;

interface PersonFactory<P extends Person> {
    P create(String name, Integer age, Boolean gender);
}
