package com.fererlab.conditional;


import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cond {

    public static Hash hash() {
        return new Hash();
    }

    public static Tree tree() {
        return new Tree();
    }

    static class Hash {

        private static Logger logger = Logger.getLogger(Tree.class.getName());
        private ExceptionUnit exceptionUnit = (e) -> {
            logger.log(Level.SEVERE, "got exception while hash cond, error: " + e, e);
        };
        private Map<Object, Unit> hash = new HashMap<>();
        private Unit defaultUnit = Unit.empty();

        public Hash check(Object key, Unit unit) {
            hash.put(key, unit);
            return this;
        }

        public Hash otherwise(Unit unit) {
            this.defaultUnit = unit;
            return this;
        }

        public Hash error(ExceptionUnit exceptionUnit) {
            this.exceptionUnit = exceptionUnit;
            return this;
        }

        private void handleError(Exception e) {
            exceptionUnit.apply(e);
        }

        public void eval(Object evalKey) {
            try {
                if (hash.containsKey(evalKey)) {
                    hash.get(evalKey).apply();
                } else {
                    defaultUnit.apply();
                }
            } catch (Exception e) {
                handleError(e);
            }
        }

    }

    static class Tree {

        class CheckUnitPair {
            private Check check;
            private Unit unit;
            private Exception exception;

            private CheckUnitPair(Check check, Unit unit) {
                this.check = check;
                this.unit = unit;
            }
        }

        private static Logger logger = Logger.getLogger(Tree.class.getName());
        private ExceptionUnit exceptionUnit = (e) -> {
            logger.log(Level.SEVERE, "got exception while tree cond, error: " + e, e);
        };
        private List<CheckUnitPair> checkUnitPairs = new LinkedList<>();
        private Unit defaultUnit = Unit.empty();

        private Tree() {
        }

        public Tree check(Check check, Unit unit) {
            checkUnitPairs.add(new CheckUnitPair(check, unit));
            return this;
        }

        public Tree otherwise(Unit unit) {
            this.defaultUnit = unit;
            return this;
        }

        public Tree error(ExceptionUnit exceptionUnit) {
            this.exceptionUnit = exceptionUnit;
            return this;
        }

        private void handleError(Exception e) {
            exceptionUnit.apply(e);
        }

        public void eval() {
            Optional<CheckUnitPair> cupOptional = checkUnitPairs
                    .stream()
                    .filter(cup -> {
                        try {
                            return cup.check.apply();
                        } catch (Exception e) {
                            cup.exception = e;
                            return true;
                        }
                    })
                    .findFirst();

            try {
                if (cupOptional.isPresent()) {
                    handleCup(cupOptional);
                } else {
                    defaultUnit.apply();
                }
            } catch (Exception e) {
                handleError(e);
            }
        }

        private void handleCup(Optional<CheckUnitPair> cupOptional) throws Exception {
            CheckUnitPair cup = cupOptional.get();
            if (cup.exception == null) {
                cup.unit.apply();
            } else {
                throw cup.exception;
            }
        }
    }

}
