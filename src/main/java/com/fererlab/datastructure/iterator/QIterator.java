package com.fererlab.datastructure.iterator;

/**
 * Iterator interface
 *
 * @param <T>
 */
public interface QIterator<T> {

    /**
     * @return returns true if more elements available
     */
    boolean hasNext();

    /**
     * @return current element
     */
    T next();

}
