package DSA.Graphs.Problems;

import DSA.Graphs.GraphGen;

import java.util.ArrayList;
import java.util.List;

public class CycleDetectionUndirectedGraph {
    static GraphGen graph = new GraphGen();

    public static void main(String[] args) {

        int n = 5;
        int[][] edges = {{1, 2}, {1, 3}, {1, 4}, {4, 5}, {2, 4}};
        for (int i = 1; i <= n; i++)
            graph.addVertex(i);
        for (int[] edge : edges)
            graph.addEdge(edge[0], edge[1], false);

        System.out.println("Cycle Present :" + containsCycle());

    }

    public static <T> boolean dfs(T node, List<T> visited, T parent) {
        visited.add(node);
        for (T nbr : (List<T>) graph.getAdjVertices().get(node)) {
            if (!visited.contains(nbr)) {
                if (dfs(nbr, visited, node)) return true;
            } else if (nbr != parent)
                return true;
        }
        return false;
    }

    public static boolean containsCycle() {
        List<Integer> visited = new ArrayList<>();
        return dfs(1, visited, -1);
    }

}
