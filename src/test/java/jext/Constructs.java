package jext;


import java.util.List;
import java.util.Map;

import static jext.Companion.*;

public class Constructs {

    public static void main(String... args) {
        Constructs constructs = new Constructs();
        constructs.constructors();
    }

    private void constructors() {
        List<String> list0 = List();
        List<String> list1 = List("a", "b", "c");
        Map<Integer, String> map0 = Map();
        Map<Integer, String> map1 = Map(Pair(1, "john"), Pair(2, "wick"));
        List<String> alist1 = ArrayList();
        List<String> llist2 = LinkedList();
        Map<Integer, String> hmap1 = HashMap();
    }

}
