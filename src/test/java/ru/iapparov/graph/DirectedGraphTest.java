package ru.iapparov.graph;

import org.junit.jupiter.api.Test;
import ru.iapparov.graph.parts.Edge;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Danis Iapparov on 29.03.2020
 *
 * @author <a href="mailto:Danis.Iapparov@t-systems.com">Danis Iapparov</a>
 */
class DirectedGraphTest {

    @Test
    public void testBuildSimplePath() {
        DirectedGraph<String> tested = new DirectedGraph<>();
        tested.addVertex("A");
        tested.addVertex("B");
        tested.addVertex("C");
        tested.addVertex("D");
        tested.addVertex("E");
        tested.addVertex("F");
        tested.addVertex("G");
        tested.addEdge("A","B");
        tested.addEdge("A","C");
        tested.addEdge("C","F");
        tested.addEdge("D","E");
        tested.addEdge("B","D");
        assertEquals(List.of(new Edge<String>("A", "B"), new Edge<String>("B", "D"),  new Edge<String>("D", "E")),
                tested.getPath("A", "E"));
    }

    @Test
    public void testBuildSimplePathWhenNoPathDueToDirectionOfEdges() {
        DirectedGraph<String> tested = new DirectedGraph<>();
        tested.addVertex("A");
        tested.addVertex("B");
        tested.addVertex("C");
        tested.addVertex("D");
        tested.addVertex("E");
        tested.addVertex("F");
        tested.addVertex("G");
        tested.addEdge("A","B");
        tested.addEdge("D","B");
        tested.addEdge("D","E");
        tested.addEdge("A","C");
        tested.addEdge("C","F");
        assertEquals(Collections.emptyList(), tested.getPath("A", "E"));
    }
}