package com.fererlab.control.expressional;

@FunctionalInterface
public interface Func<T> {

    Object EMPTY = new Object();

    T apply();

}
