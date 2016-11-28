package com.fererlab.datastructure.iterator;

import com.fererlab.datastructure.util.Maybe;

public abstract class Iterator<T> implements CIterator<T>, QIterator<T> {

    private int index = 0;

    protected abstract int getSize();

    protected abstract Maybe<T> get(int index);

    protected abstract void remove(int index);

    public boolean hasNext() {
        return index < getSize();
    }

    @Override
    public T next() {
        T value = get(index).get();
        index++;
        return value;
    }

    @Override
    public void remove() {
        index--;
        remove(index);
    }

}
