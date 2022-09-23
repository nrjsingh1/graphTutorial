package DSA.Graphs;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Getter
@Setter
public class GraphGen<T> {

    private Map<T, List<T>> adjVertices;

    public GraphGen() {
        adjVertices = new HashMap<>();
    }

    public void addVertex(T label) {
        if (!adjVertices.containsKey(label)) {
            adjVertices.put(label, new ArrayList<>());
            log.info("Added vertex: {}", label);
        }
    }

    public void addEdge(T label1, T label2, boolean dir) {
        adjVertices.get(label1).add(label2);
        if (!dir)
            adjVertices.get(label2).add(label1);
        log.info("Added edge: {} {}. Directed :{}", label1, label2, dir);
    }

    public void removeVertex(T label) {
        //first remove the vertex from the values
        adjVertices.values().stream().forEach(vertices -> vertices.remove(label));
        //now remove the vertex from the key
        adjVertices.remove(label);
        log.info("Removed Vertex :{}", label);
    }

    public void removeEdge(T v1, T v2) {
        List<T> ev1 = adjVertices.get(v1);
        List<T> ev2 = adjVertices.get(v2);
        if (ev1.contains(v2)) {
            ev1.remove(v2);
            log.info("Removed Edge : {} {}", v1, v2);
        } else
            log.info("Edge Doesnot Exist {} - {}", v1, v2);
        if (ev2.contains(v1)) {
            ev2.remove(v1);
            log.info("Removed Edge : {} {}", v1, v2);
        } else
            log.info("Edge Doesnot Exist {} - {}", v2, v1);
    }

    public List<T> getNeighbours(T label) {
        return adjVertices.get(label);
    }

    public void printGraphInList() {
        adjVertices.keySet().stream().forEach(vertex -> {
            log.info("Node : {} -> {}", vertex, adjVertices.get(vertex));
        });
    }

    public Set<T> dfs(T src) {
        Set<T> visited = new LinkedHashSet<>();
        Stack<T> stack = new Stack<>();
        Map<T, String> path = new HashMap<>();
        stack.push(src);
        visited.add(src);
        path.put(src, String.valueOf(src));
        while (!stack.isEmpty()) {
            T cur = stack.pop();
            for (T cur_v : getAdjVertices().get(cur)) {
                if (!visited.contains(cur_v)) {
                    path.put(cur_v, path.get(cur) + " " + cur_v);
                    stack.push(cur_v);
                    visited.add(cur_v);
                }
            }

        }
        for (T key : path.keySet())
            log.info(" Node {} Path -{}", key, path.get(key));
        return visited;
    }

}
