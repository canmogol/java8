package com.fererlab.control.expressional;


import com.fererlab.control.ControlException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Match {

    private static Logger logger = Logger.getLogger(Match.class.getName());
    private ControlException exceptionFunc = (e) -> {
        logger.log(Level.SEVERE, "got exception while matching, error: " + e, e);
    };
    private Map<Object, Func> hash = new HashMap<>();
    private Optional<Func> defaultFunc = Optional.empty();

    public static Match Match() {
        return new Match();
    }

    public Match check(Object key, Func unit) {
        hash.put(key, unit);
        return this;
    }

    public Match otherwise(Func func) {
        this.defaultFunc = Optional.ofNullable(func);
        return this;
    }

    public Match error(ControlException exceptionFunc) {
        this.exceptionFunc = exceptionFunc;
        return this;
    }

    private void handleError(Exception e) {
        exceptionFunc.apply(e);
    }

    public Optional eval(Object evalKey) {
        Optional<Object> result = Optional.empty();
        try {
            if (hash.containsKey(evalKey)) {
                result = Optional.ofNullable(hash.get(evalKey).apply());
            } else if (defaultFunc.isPresent()) {
                result = Optional.ofNullable(defaultFunc.get());
            }
        } catch (Exception e) {
            handleError(e);
        }
        return (Optional) result;
    }

}
