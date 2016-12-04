package com.fererlab.datastructure.util;

/**
 * utility class Maybe, simple object wrapper to prevent null pointer exceptions
 *
 * @param <T>
 */
public class Maybe<T> {

    /**
     * internal object
     */
    private T object;

    /**
     * empty state {}
     */
    private boolean empty = true;

    private Maybe(T object) {
        this.setObject(object);
    }

    private void setObject(T object) {
        this.object = object;
        if (this.object != null) {
            this.empty = false;
        }
    }

    public static <T> Maybe<T> empty() {
        return new Maybe<>(null);
    }

    public static <T> Maybe<T> create(T t) {
        return new Maybe<>(t);
    }

    public T get() {
        return object;
    }

    public T orElse(T t) {
        return isEmpty() ? t : get();
    }

    public T orElseThrow(Exception e) throws Exception {
        if (isEmpty()) {
            throw e;
        } else {
            return get();
        }
    }

    public boolean isEmpty() {
        return empty;
    }

}
