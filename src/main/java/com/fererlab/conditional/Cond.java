package com.fererlab.conditional;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cond {

    public static Hash hash() {
        return new Hash();
    }

    public static Tree tree() {
        return new Tree();
    }

    public static Tree tree(ExceptionUnit exceptionUnit) {
        Tree tree = new Tree();
        tree.error(exceptionUnit);
        return tree;
    }

    static class Hash {

        private static Logger logger = Logger.getLogger(Tree.class.getName());
        private ExceptionUnit exceptionUnit = (e) -> {
            logger.log(Level.SEVERE, "got exception while cond, error: " + e, e);
        };
        private Map<Object, Unit> hash = new HashMap<>();
        private Unit defaultUnit = () -> {
        };

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

        private static Logger logger = Logger.getLogger(Tree.class.getName());
        private ExceptionUnit exceptionUnit = (e) -> {
            logger.log(Level.SEVERE, "got exception while cond, error: " + e, e);
        };
        private State state = State.False;

        private Tree() {
        }

        public Tree check(Check check, Unit unit) {
            if (!State.False.equals(this.state)) {
                return this;
            }
            try {
                this.state = check.apply() ? State.True : State.False;
                if (State.True.equals(this.state)) {
                    unit.apply();
                }
            } catch (Exception e) {
                handleError(e);
            }
            return this;
        }

        public Tree otherwise(Unit unit) {
            if (State.False.equals(this.state)) {
                try {
                    unit.apply();
                } catch (Exception e) {
                    handleError(e);
                }
            }
            return this;
        }

        public Tree error(ExceptionUnit exceptionUnit) {
            this.exceptionUnit = exceptionUnit;
            return this;
        }

        private void handleError(Exception e) {
            this.state = State.Exception;
            exceptionUnit.apply(e);
        }

    }

    enum State {
        True, False, Exception
    }

}
