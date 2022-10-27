package DSA.Graphs.Problems;

import java.util.Arrays;

public class IsGraphBipartite {
    public static void main(String[] args) {
        int adjList[][] = {{1,3},{0,2},{1,3},{0,2}} /* {{1,2,3},{0,2},{0,1,3},{0,2}}*/ ;
        int n = adjList.length;
        int[] visited = new int[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        boolean res = true;
        for(int i =0; i <n ; i++){
            if(visited[i]==0) {
                res = dfs(adjList, i, parent, 1, visited);
                if(res == false) break;
            }
        }
        if(!res)
        System.out.println(" Not Bipartite");
        else System.out.println("Bipartite");
    }



    public static boolean dfs(int[][] graph,  int current , int[] parent, int color, int[] visited){
        visited[current] = color;
        for(int nbr : graph[current]){
            if(visited[nbr]==0){//not visited
                parent[nbr] = current;
                boolean subProb = dfs(graph, nbr, parent, 3-color, visited);
                if(subProb == false) return false;
            }
            else{
                if(color == visited[nbr])
                    return false;
            }
        }
        return true;
    }
}
