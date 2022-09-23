package DSA.Graphs.Problems;

import DSA.Graphs.GraphGen;

import java.util.List;

public class CycleDetectionDirectedGraph {
    static GraphGen graph = new GraphGen();

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{2, 1}, {3, 2}, {3, 1}, {2, 4}, {4, 5}, {5, 3}};
        for (int i = 1; i <= n; i++)
            graph.addVertex(i);

        for (int[] edge : edges)
            graph.addEdge(edge[0], edge[1], true);
        boolean[] visited = new boolean[n + 1];//by default false if using Boolean object then use Arrays.fill with false
        boolean[] callStack = new boolean[n + 1];
        for(int i =1; i<=n && visited[i]==false; i++){
            if(dfs(i, visited, callStack)) {
                System.out.println("Cycle found for node - " + i);
                return;
            }
        }
        System.out.println("Cycle not found for any node");
    }

    public static boolean dfs(int node, boolean[] visited, boolean[] callStack) {
        visited[node] = true;
        callStack[node] = true;
        for (int nbr : (List<Integer>) graph.getNeighbours(node)) {
            if (visited[nbr] && callStack[nbr]) return true;
            else if (!visited[nbr])
                if (dfs(nbr, visited, callStack)) return true;
        }
        callStack[node] = false;
        return false;
    }
}
