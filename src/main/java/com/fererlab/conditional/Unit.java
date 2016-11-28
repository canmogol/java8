package com.fererlab.conditional;

@FunctionalInterface
public interface Unit {
    void apply();

    static Unit empty() {
        return () -> {
        };
    }
}
