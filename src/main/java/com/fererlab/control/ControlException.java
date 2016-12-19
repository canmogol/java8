package com.fererlab.control;

@FunctionalInterface
public interface ControlException {
    void apply(Exception e);
}
