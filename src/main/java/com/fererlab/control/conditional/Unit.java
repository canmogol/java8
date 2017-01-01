package com.fererlab.control.conditional;

@FunctionalInterface
public interface Unit {
    static Unit empty() {
        return () -> {
        };
    }

    void apply();
}
