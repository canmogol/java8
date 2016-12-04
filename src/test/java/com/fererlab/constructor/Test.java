package com.fererlab.constructor;

import java.lang.reflect.InvocationTargetException;

public class Test {

    public static void main(String[] args) {
        try {
            FilterPart filterPart = new FilterPart();
            int arg = 4;
            Pojo pojo = (Pojo) Pojo.class.getConstructor(Integer.class).newInstance(arg);
            System.out.println("pojo = " + pojo.getAge());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
