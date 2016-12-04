package com.fererlab.datastructure.iterator;

/**
 * iteration interface for collections
 */
public interface Iterable<T> {

    /**
     * returns iterator for collection
     *
     * @return Iterator
     */
    Iterator<T> iterator();

}
