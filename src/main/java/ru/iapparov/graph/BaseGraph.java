package ru.iapparov.graph;

import ru.iapparov.graph.parts.Edge;
import ru.iapparov.graph.parts.Vertex;
import ru.iapparov.graph.util.CloneUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Created by Danis Iapparov on 25.03.2020
 *
 * @author <a href="mailto:Danis.Iapparov@t-systems.com">Danis Iapparov</a>
 */
abstract class BaseGraph<V> implements Graph<V>  {
    protected Map<Vertex<V>, Set<Vertex<V>>> vertices = new HashMap<>();

    @Override
    public void addVertex(V vertex) {
        vertices.putIfAbsent(Vertex.of(vertex), new HashSet<>());
    }

    @Override
    public boolean addEdge(V rawVertexFrom, V rawVertexTo) {
        Vertex<V> vertexFrom = Vertex.of(rawVertexFrom);
        Vertex<V> vertexTo = Vertex.of(rawVertexTo);
        checkVerticesPresent(vertexFrom, vertexTo);

        registerEdge(vertexFrom, vertexTo);
        return true;
    }

    @Override
    public List<Edge<V>> getPath(V rawVertexFrom, V rawVertexTo) {
        checkVerticesPresent(Vertex.of(rawVertexFrom), Vertex.of(rawVertexTo));
        if(Objects.equals(rawVertexFrom, rawVertexTo)) {
            return Collections.emptyList();
        }

        var localVertices = CloneUtils.cloneWithReferences(vertices, HashMap::new);
        Vertex<V> vertexFrom = getVertexReference(rawVertexFrom, localVertices);
        Vertex<V> vertexTo = getVertexReference(rawVertexTo, localVertices);

        List<Vertex<V>> rawResult = getPath(vertexFrom, vertexTo, localVertices);
        return buildEdges(rawResult);
    }

    private Vertex<V> getVertexReference(V rawVertexFrom, Map<Vertex<V>, Set<Vertex<V>>> localVertices) {
        for (Vertex<V> vertex: localVertices.keySet()) {
            if (Objects.equals(vertex.getValue(), rawVertexFrom)) {
                return vertex;
            }
        }

        //unreachable (i hope)
        return null;
    }

    /**
     * breadth-first search
     */
    private List<Vertex<V>> getPath(Vertex<V> from, Vertex<V> to, Map<Vertex<V>, Set<Vertex<V>>> localVertices) {
        Set<Vertex<V>> edges = localVertices.get(from);
        for (Vertex<V> target: edges) {
            if (target.equals(to)) {
                List<Vertex<V>> result = new ArrayList<>();
                result.add(to);
                result.add(from);
                return result;
            }
        }

        from.setVisited();

        var pathOptional = edges.stream()
                .filter(Predicate.not(Vertex::isVisited))
                .map(edge -> getPath(edge, to, localVertices))
                .filter(Objects::nonNull)
                .findAny();

        return pathOptional.map(path -> {
            path.add(from);
            return path;
        }).orElse(null);
    }

    private List<Edge<V>> buildEdges(List<Vertex<V>> vertices) {
        if (vertices == null) {
            return Collections.emptyList();
        }

        Collections.reverse(vertices);

        var result = new ArrayList<Edge<V>>();
        Vertex<V> from = null;
        for (var to : vertices) {
            if (from != null) {
                result.add(buildEdge(from.getValue(), to.getValue()));
            }
            from = to;
        }
        return result;
    }

    @SafeVarargs
    private void checkVerticesPresent(Vertex<V> ... verticesToCheck) {
        List<Vertex<V>> notPresent = new ArrayList<>();
        for(Vertex<V> o: verticesToCheck) {
            if (!vertices.containsKey(o)) {
                notPresent.add(o);
            }
        }
        if (!notPresent.isEmpty()) {
            throw new IllegalArgumentException("Vertices are not present: " + notPresent);
        }
    }

    private Edge<V> buildEdge(V vertexFrom, V vertexTo) {
        return new Edge<V>(vertexFrom, vertexTo);
    }

    abstract void registerEdge(Vertex<V> vertexFrom, Vertex<V> vertexTo);
}
