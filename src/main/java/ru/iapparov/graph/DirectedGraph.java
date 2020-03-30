package ru.iapparov.graph;

import ru.iapparov.graph.parts.Vertex;

/**
 * Created by Danis Iapparov on 25.03.2020
 *
 * @author <a href="mailto:Danis.Iapparov@t-systems.com">Danis Iapparov</a>
 */
public class DirectedGraph<V> extends BaseGraph<V> {

    @Override
    void registerEdge(Vertex<V> vertexFrom, Vertex<V> vertexTo) {
        vertices.get(vertexFrom).add(vertexTo);
    }
}
