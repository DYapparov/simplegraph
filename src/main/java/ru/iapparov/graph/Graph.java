package ru.iapparov.graph;

import ru.iapparov.graph.parts.Edge;

import java.util.List;

/**
 * Created by Danis Iapparov on 25.03.2020
 *
 * @author <a href="mailto:Danis.Iapparov@t-systems.com">Danis Iapparov</a>
 */
public interface Graph<V> {
    void addVertex(V vertex);
    boolean addEdge(V vertexFrom, V vertexTo);
    List<Edge<V>> getPath(V vertexFrom, V vertexTo);
}
