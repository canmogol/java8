package jext;


import java.util.*;
import java.util.stream.Collectors;

public class Companion {

    @SafeVarargs
    public static <T> List<T> List(T... values) {
        List<T> ts = new ArrayList<>(values.length);
        ts.addAll(Arrays.asList(values));
        return Collections.unmodifiableList(ts);
    }

    @SafeVarargs
    public static <K, V> Map<K, V> Map(Pair<K, V>... pairs) {
        Map<K, V> map = Arrays.asList(pairs)
                .stream()
                .collect(
                        Collectors.toMap(
                                Pair::getKey,
                                Pair::getValue,
                                (u, v) -> {
                                    throw new IllegalStateException(String.format("Duplicate key %s", u));
                                },
                                LinkedHashMap::new));
        return Collections.unmodifiableMap(map);
    }

    public static <K, V> Pair<K, V> Pair(K key, V value) {
        return new Pair<K, V>(key, value);
    }

    public static <T> ArrayList<T> ArrayList() {
        return new ArrayList<>();
    }

    public static <T> LinkedList<T> LinkedList() {
        return new LinkedList<>();
    }

    public static <K, V> HashMap<K, V> HashMap() {
        return new HashMap<>();
    }

}
