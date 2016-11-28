package com.fererlab.datastructure.node;

public interface QNode<T> {

    T getValue();

    QNode<T> getPrevious();

    QNode<T> getNext();

}
