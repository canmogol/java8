package com.fererlab.datastructure.node;

public interface CNode<T> extends QNode<T> {

    void setValue(T value);

    void setPrevious(QNode<T> previous);

    void setNext(QNode<T> next);

}
