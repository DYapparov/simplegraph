package ru.iapparov.graph.util;

import ru.iapparov.graph.parts.Vertex;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by Danis Iapparov on 25.03.2020
 *
 * @author <a href="mailto:Danis.Iapparov@t-systems.com">Danis Iapparov</a>
 */
public class CloneUtils {
    private CloneUtils() {

    }

    public static <V,C extends Set<Vertex<V>>> C clone(Set<Vertex<V>> toClone, Supplier<C> setSupplier) {
        return toClone.stream()
                .map(Vertex::copy)
                .collect(Collectors.toCollection(setSupplier));
    }

    public static <V,C extends Set<Vertex<V>>> C clone(Set<Vertex<V>> toClone, Map<Vertex<V>, Vertex<V>> vertices, Supplier<C> setSupplier) {
        return toClone.stream()
                .map(vertices::get)
                .collect(Collectors.toCollection(setSupplier));
    }

    public static <V> Map<Vertex<V>, Set<Vertex<V>>> cloneWithReferences(Map<Vertex<V>, Set<Vertex<V>>> toClone, Supplier<Map<Vertex<V>, Set<Vertex<V>>>> mapSupplier) {
        var copy = mapSupplier.get();
        var vertices = clone(toClone.keySet(), HashSet::new)
                .stream()
                .collect(Collectors.toUnmodifiableMap(Function.identity(), Function.identity()));
        toClone.forEach((key, value) -> copy.put(vertices.get(key), clone(value, vertices, HashSet::new)));
        return copy;
    }
}
