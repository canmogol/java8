package com.fererlab.datastructure.collection;

import com.fererlab.datastructure.util.Maybe;

/**
 * Query interface of Collection
 *
 * @param <T>
 */
public interface QCollection<T> {

    /**
     * returns the value at the index
     *
     * @param index int value position
     * @return returns Maybe of value at index
     */
    Maybe<T> get(int index);

    /**
     * find if value exists
     *
     * @param value value to search
     * @return true if any value exists
     */
    boolean contains(T value);

    /**
     * returns the size of the collection
     *
     * @return int size of the collection
     */
    int getSize();

}
