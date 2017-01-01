package com.fererlab.datastructure.node;

/**
 * Mutable node, implements {@code QNode} and {@code CNode}
 *
 * @param <T>
 */
public class Node<T> implements QNode<T>, CNode<T> {

    private T value;
    private QNode<T> previous;
    private QNode<T> next;

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public QNode<T> getPrevious() {
        return previous;
    }

    @Override
    public void setPrevious(QNode<T> previous) {
        this.previous = previous;
    }

    @Override
    public QNode<T> getNext() {
        return next;
    }

    @Override
    public void setNext(QNode<T> next) {
        this.next = next;
    }

}
