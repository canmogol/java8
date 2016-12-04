package com.fererlab.datastructure;

import com.fererlab.datastructure.iterator.Iterator;
import com.fererlab.datastructure.list.linked.LinkedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTester {

    // testing value
    private final String testValueOne = "Value One";
    private final String testValueTwo = "Value Two";
    private final String testValueThree = "Value Three";

    // linked list reference to test
    private LinkedList<String> list;

    @Before
    public void prepare() {
        // create empty list
        list = new LinkedList<>();
    }

    @Test
    public void zeroSizeTest() throws Exception {
        // initial size should be zero
        Assert.assertEquals(list.getSize(), 0);
    }

    @Test
    public void addRemoveValueTest() throws Exception {
        // add value, list value should be 1
        list.add(testValueOne);
        Assert.assertEquals(list.getSize(), 1);

        // list should contain test value
        Assert.assertTrue(list.contains(testValueOne));

        // first one should be our value
        Assert.assertEquals(list.get(0).orElseThrow(new RuntimeException("no value found at this index")), testValueOne);

        // head and tail should be our value
        Assert.assertEquals(list.getHead().getValue(), testValueOne);
        Assert.assertEquals(list.getTail().getValue(), testValueOne);

        // after remove size should be 0
        list.remove(0);
        Assert.assertEquals(list.getSize(), 0);
    }

    @Test
    public void clearTest() throws Exception {

        // after adding size should be 1
        list.add(testValueOne);
        Assert.assertEquals(list.getSize(), 1);

        // after remove size should be 0
        list.remove(testValueOne);
        Assert.assertEquals(list.getSize(), 0);

        // after adding size should be 1
        list.add(testValueOne);
        Assert.assertEquals(list.getSize(), 1);

        // after clear size should be 0
        list.clear();
        Assert.assertEquals(list.getSize(), 0);

    }

    @Test
    public void removeOutOfIndexTest() throws Exception {

        // after adding size should be 1
        list.add(testValueOne);
        Assert.assertEquals(list.getSize(), 1);

        // after adding size should be 1
        list.add(testValueTwo);
        Assert.assertEquals(list.getSize(), 2);

        // after adding size should be 1
        list.add(testValueThree);
        Assert.assertEquals(list.getSize(), 3);

        // remove index greater than zero, this should NOT raise an exception!!!
        list.remove(99);
    }

    @Test
    public void removeOneValueTest() throws Exception {

        // after adding size should be 1
        list.add(testValueOne);
        Assert.assertEquals(list.getSize(), 1);

        // after adding size should be 1
        list.add(testValueTwo);
        Assert.assertEquals(list.getSize(), 2);

        // after adding size should be 1
        list.add(testValueThree);
        Assert.assertEquals(list.getSize(), 3);

        // get iterator and remove one value
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            if (testValueTwo.equals(value)) {
                iterator.remove();
                System.out.println("removed value = " + value);
            } else {
                System.out.println("preserved value = " + value);
            }
        }
        // after removing only one value, size should be 2
        Assert.assertEquals(list.getSize(), 2);
    }

    @Test
    public void removeAllValuesTest() throws Exception {

        // after adding size should be 1
        list.add(testValueOne);
        Assert.assertEquals(list.getSize(), 1);

        // after adding size should be 1
        list.add(testValueTwo);
        Assert.assertEquals(list.getSize(), 2);

        // after adding size should be 1
        list.add(testValueThree);
        Assert.assertEquals(list.getSize(), 3);

        // get iterator and remove all values
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            System.out.println("removing value = " + value);
            iterator.remove();
        }
        // after removing only all values, size should be 0
        Assert.assertEquals(list.getSize(), 0);
    }

}
