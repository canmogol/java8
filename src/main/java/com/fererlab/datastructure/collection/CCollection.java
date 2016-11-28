package com.fererlab.datastructure.collection;

/**
 * Command interface of Collection
 */
public interface CCollection<T> {

    /**
     * adds value to the collection
     *
     * @param value object value
     */
    void add(T value);


    /**
     * removes the value at the index
     *
     * @param index index of the value
     */
    void remove(int index);


    /**
     * remove all the values with matching value
     *
     * @param value object value
     */
    void remove(T value);


    /**
     * clears the collection, sets size to 0, removes all references and sets values to null
     */
    void clear();

}
