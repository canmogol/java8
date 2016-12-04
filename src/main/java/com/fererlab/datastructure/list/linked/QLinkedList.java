package com.fererlab.datastructure.list.linked;

import com.fererlab.datastructure.collection.QCollection;
import com.fererlab.datastructure.node.QNode;

/**
 * Query interface of linked list
 */
public interface QLinkedList<T> extends QCollection<T> {

    /**
     * returns the head Node
     *
     * @return QNode the head node
     */
    QNode<T> getHead();


    /**
     * returns the tail node
     *
     * @return {@code QNode} the tail node
     */
    QNode<T> getTail();

}
