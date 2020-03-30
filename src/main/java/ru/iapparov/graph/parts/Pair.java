package ru.iapparov.graph.parts;

/**
 * Created by Danis Iapparov on 29.03.2020
 *
 * @author <a href="mailto:Danis.Iapparov@t-systems.com">Danis Iapparov</a>
 */
public class Pair<V> {
    private V first;
    private V second;

    private Pair(V first, V second) {
        this.first = first;
        this.second = second;
    }

    public static <V>Pair<V> of(V first, V second) {
        return new Pair<>(first, second);
    }

    public V getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }
}
