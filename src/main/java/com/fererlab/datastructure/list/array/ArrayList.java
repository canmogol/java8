package com.fererlab.datastructure.list.array;

import com.fererlab.datastructure.iterator.Iterable;
import com.fererlab.datastructure.iterator.Iterator;
import com.fererlab.datastructure.util.Maybe;

/**
 * final class ArrayList, implements Query and Command interfaces of ArrayList
 *
 * @param <T> generic type of the value
 */
public final class ArrayList<T> implements QArrayList<T>, CArrayList<T>, Iterable<T> {

    private int step;
    private Object[] objects;
    private int size = 0;

    public ArrayList() {
        this(10);
    }

    public ArrayList(int initialCapacity) {
        this.step = initialCapacity;
        this.objects = new Object[step];
    }

    @Override
    public void add(T value) {
        if (size >= objects.length - 1) {
            expand();
        }
        objects[size] = value;
        size++;
    }

    private void expand() {
        Object[] temp = objects;
        objects = new Object[objects.length + step];
        System.arraycopy(temp, 0, objects, 0, temp.length);
    }

    @Override
    public void remove(int index) {
        if (index > 0 && index < objects.length - 1) {
            // the object to be removed
            Object removed = objects[index];
            // set null to that index, object will be GC'd
            objects[index] = null;
            /**
             * size     index       object array length
             * 6                    10
             * 1-2-3-4-5-6-*-*-*-*
             *
             * 6        3           10
             * 1-2-3-[4]-5-6-*-*-*-*
             * 1-2-3-X-5-6-*-*-*-*-*
             * 1-2-3-5-6-*-*-*-*-*-*
             *
             */
            // number of elements to move
            int numberOfElementsToMove = size - index;
            // this might be the last element, if so do nothing
            if (numberOfElementsToMove > 0) {
                System.arraycopy(objects, index, objects, index - 1, numberOfElementsToMove);
            }
        }
    }

    @Override
    public void remove(T value) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Maybe<T> get(int index) {
        return null;
    }

    @Override
    public boolean contains(T value) {
        return false;
    }

    @Override
    public int getSize() {
        return 0;
    }

}
