package ru.iapparov.graph.parts;

import java.util.Objects;

/**
 * Created by Danis Iapparov on 25.03.2020
 *
 * @author <a href="mailto:Danis.Iapparov@t-systems.com">Danis Iapparov</a>
 */
public class Vertex <V> {
    private V value;
    private boolean visited;

    private Vertex(V value) {
        this.value = value;
    }

    public static <V> Vertex<V> of(V value) {
        return new Vertex<>(value);
    }

    public Vertex<V> copy() {
        return new Vertex<>(value);
    }

    public V getValue() {
        return value;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited() {
        visited = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return Objects.equals(value, vertex.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "value=" + value +
                '}';
    }
}
