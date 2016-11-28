package com.fererlab.datastructure.list.linked;

import com.fererlab.datastructure.iterator.Iterable;
import com.fererlab.datastructure.iterator.Iterator;
import com.fererlab.datastructure.node.CNode;
import com.fererlab.datastructure.node.Node;
import com.fererlab.datastructure.node.QNode;
import com.fererlab.datastructure.util.Maybe;

/**
 * final class LinkedList, implements Query and Command interfaces of LinkedList
 *
 * @param <T> generic type of the Node's value
 */
public final class LinkedList<T> implements QLinkedList<T>, CLinkedList<T>, Iterable<T> {

    /**
     * internal iterator class
     */
    class InternalIterator extends Iterator<T> {

        @Override
        protected int getSize() {
            return LinkedList.this.getSize();
        }

        @Override
        protected Maybe<T> get(int currentIndex) {
            return LinkedList.this.get(currentIndex);
        }

        @Override
        protected void remove(int currentIndex) {
            LinkedList.this.remove(currentIndex);
        }
    }

    /**
     * head the last inserted node of the linked list
     */
    private CNode<T> head;

    /**
     * tail the first inserted node of the linked list
     */
    private CNode<T> tail;

    /**
     * current size of the linked list
     */
    private int size = 0;

    /**
     * adds value to the list
     *
     * @param value generic type T
     */
    @Override
    public void add(T value) {
        /**
         *  head [ADD] -> NULL <- tail
         *
         *  head [ADD] -> N1[v:NULL:NULL] <- tail
         *
         *  head [ADD] -> N2[v:NULL:*N1] -> N1[v:*N2:NULL] <- tail
         *
         *  head [ADD] -> N3[v:NULL:*N2] -> N2[v:*N3:*N1] -> N1[v:*N2:NULL] <- tail
         *
         */

        // first create a node for this value
        CNode<T> node = new Node<T>();
        node.setValue(value);

        // than add this node to list
        CNode<T> temp = head;
        head = node;
        node.setPrevious(null);
        node.setNext(temp);
        if (temp != null) {
            temp.setPrevious(node);
        }
        // if the size is zero, than the head and tail points to this node
        if (getSize() == 0) {
            tail = head;
        }
        // no matter what, increase the size by one
        size++;
    }

    /**
     * removes the value at the index
     *
     * @param index index of the value
     */
    @Override
    public void remove(int index) {
        QNode<T> node = findNodeAtIndex(index);
        // if there is a node found remove that node, otherwise do nothing, index may greater then size-1,
        if (node != null) {
            unlink(node);
        }
    }

    /**
     * remove all the matching values
     *
     * @param value value object
     */
    @Override
    public void remove(T value) {
        for (QNode<T> currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
            if (currentNode.getValue().equals(value)) {
                unlink(currentNode);
            }
        }
    }

    /**
     * clears the list, sets size to 0, removes all references and sets values to null
     */
    @Override
    public void clear() {
        for (CNode<T> currentNode = head; currentNode != null; ) {
            // casting CNode is ok, since only CNode can be added, see add method
            CNode<T> next = (CNode<T>) currentNode.getNext();
            currentNode.setNext(null);
            currentNode.setPrevious(null);
            currentNode.setValue(null);
            currentNode = next;
        }
        head = tail = null;
        size = 0;
    }

    /**
     * returns a new internal iterator of linked list
     *
     * @return Iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new InternalIterator();
    }

    /**
     * returns the value at the index
     *
     * @param index int value position
     * @return Maybe of value type at index
     */
    @Override
    public Maybe<T> get(int index) {
        T value = null;
        QNode<T> node = findNodeAtIndex(index);
        // if there is a node found remove that node, otherwise do nothing, index may greater then size-1,
        if (node != null) {
            value = node.getValue();
        }
        return Maybe.create(value);
    }

    /**
     * find if any value exists
     *
     * @param value value to search
     * @return true if any value exists
     */
    @Override
    public boolean contains(T value) {
        boolean contains = false;
        for (QNode<T> currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
            if (currentNode.getValue().equals(value)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    /**
     * finds the node at the index
     *
     * @param index position of node
     * @return the node at the position
     */
    private QNode<T> findNodeAtIndex(int index) {
        // return null if the index is greater then the size-1
        if (index > getSize() - 1) {
            return null;
        }
        // check if the index is closer to head
        if (index < (getSize() >> 1)) {
            // start from head
            QNode<T> currentNode = head;
            for (int i = 0; i < index; i++) {
                // get the next node
                currentNode = currentNode.getNext();
            }
            // at this point the current node is the node at the index
            return currentNode;
        } else {
            // index is closer to tail
            // start from tail
            QNode<T> currentNode = tail;
            for (int i = getSize() - 1; i > index; i--) {
                currentNode = currentNode.getPrevious();
            }
            // at this point the current node is the node at the index
            return currentNode;
        }
    }

    /**
     * internal method to remove a node, this method will also set previous and next nodes' references
     *
     * @param node Node to be removed
     */
    private void unlink(QNode<T> node) {
        // casting CNode is ok, since only CNodes can be added
        CNode<T> previous = (CNode<T>) node.getPrevious();
        CNode<T> next = (CNode<T>) node.getNext();
        /**
         *  head [ADD] -> NULL <- tail
         *
         *  head [ADD] -> N1[v:NULL:NULL] <- tail
         *
         *  head [ADD] -> N2[v:NULL:*N1] -> N1[v:*N2:NULL] <- tail
         *
         *  head [ADD] -> N3[v:NULL:*N2] -> N2[v:*N3:*N1] -> N1[v:*N2:NULL] <- tail
         *
         */
        if (previous == null) {
            // this node is the current head, will set the head to it's next node
            head = next;
        } else {
            // this node is a middle or tail node, will set the node's previous's next to this node's next
            previous.setNext(next);
        }

        if (next == null) {
            // this node is the current tail, will set the tail to it's previous node
            tail = previous;
        } else {
            // this node is a middle or head node, will set the node's next's previous to this node's previous
            next.setPrevious(previous);
        }

        // there are no nodes in the list has a reference to this node
        // will set this node to NULL,
        // since there is no other reference to it's object,
        // it's object should be GC'ed at next GC
        node = null;

        // reduce the size by one
        size--;
    }

    /**
     * returns the head Node
     *
     * @return QNode the head node
     */
    @Override
    public QNode<T> getHead() {
        return head;
    }

    /**
     * returns the tail node
     *
     * @return {@code QNode} the tail node
     */
    @Override
    public QNode<T> getTail() {
        return tail;
    }

    /**
     * returns the size of the list
     *
     * @return int size of the list
     */
    @Override
    public int getSize() {
        return size;
    }

}
