package com.fererlab.java8;

public class ModuleOne implements IModuleOne {
    @Override
    public String sayHi(String name) {
        return "Hi " + name;
    }
}
