package ru.iapparov.graph.parts;

import java.util.Objects;

/**
 * Created by Danis Iapparov on 25.03.2020
 *
 * @author <a href="mailto:Danis.Iapparov@t-systems.com">Danis Iapparov</a>
 */
public class Edge<V> implements Cloneable {
    private Vertex<V> first;
    private Vertex<V> second;

    public Edge(V first, V second) {
        this.first = Vertex.of(first);
        this.second = Vertex.of(second);
    }

    public V getFirst() {
        return first.getValue();
    }

    public V getSecond() {
        return second.getValue();
    }

    Vertex<V> getFirstVertex() {
        return first;
    }

    Vertex<V> getSecondVertex() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return Objects.equals(first, edge.first) &&
                Objects.equals(second, edge.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
