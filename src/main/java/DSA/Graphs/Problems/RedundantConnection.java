package DSA.Graphs.Problems;

import DSA.Graphs.GraphGen;

import java.util.ArrayList;
import java.util.List;

public class RedundantConnection {

    static GraphGen graph = new GraphGen();//dont use static in submission
    public static void main(String[] args) {
        int [][] edges = {{1,2},{1,3},{2,3}};
        int n = edges.length;
        for(int i =1; i<=n ; i++)
            graph.addVertex(i);
        for(int[] edge : edges){
            List<Integer> visited = new ArrayList<>();
            if(dfs(edge[0], visited, -1, edge[1]))//check connection or cycle detection
                System.out.println(edge[0] +" , "+edge[1]);
            else
                graph.addEdge(edge[0], edge[1], false);
        }
        System.out.println("null");
    }

    public  static  boolean dfs(int node , List<Integer> visited, int parent, int dest){
        visited.add(node);
        for(int nbr :(List<Integer>) graph.getAdjVertices().get(node)){
            if(nbr ==dest && nbr != parent){
                return true;
            }
            else if(!visited.contains(nbr)){
                boolean connected = dfs(nbr, visited, node, dest);
                if(connected) return true;
            }
        }
        return false;
    }
}
