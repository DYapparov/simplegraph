package ru.iapparov.graph;

import org.junit.jupiter.api.Test;
import ru.iapparov.graph.parts.Edge;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Danis Iapparov on 29.03.2020
 *
 * @author <a href="mailto:Danis.Iapparov@t-systems.com">Danis Iapparov</a>
 */
class UndirectedGraphTest {

    @Test
    public void testBuildSimplePath() {
        UndirectedGraph<String> tested = new UndirectedGraph<>();
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
        tested.addEdge("D","B");
        assertEquals(List.of(new Edge<String>("A", "B"), new Edge<String>("B", "D"),  new Edge<String>("D", "E")),
                tested.getPath("A", "E"));
    }
}