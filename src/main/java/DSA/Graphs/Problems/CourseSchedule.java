package DSA.Graphs.Problems;

import DSA.Graphs.GraphGen;

import java.util.List;

public class CourseSchedule {
    public static void main(String[] args) {

        int numCourses = 7;
        int[][] prerequisites = {{1,4},{2,4},{3,1},{3,2},{5,6},{6,5}};
        GraphGen graph = new GraphGen();
        for(int i =0; i< numCourses ; i++)
            graph.addVertex(i);

        for(int[] edge: prerequisites)
            graph.addEdge(edge[0], edge[1], true);

        boolean[] visited = new boolean[numCourses];
        boolean[] callStack = new boolean[numCourses];
        for(int i =0; i<numCourses; i++){
            if(dfsContainsCycle(i, visited, callStack, graph)) {
                System.out.println("Cant finish course" + i);
                return;
            }
        }
        System.out.println("Can finish course");
    }

    public static boolean dfsContainsCycle(int node, boolean[] visited, boolean[] callStack, GraphGen graph){
        visited[node] = true;
        callStack[node] = true;
        for(int nbr : (List<Integer>) graph.getNeighbours(node)){
            if(callStack[nbr]) return true;
            else if(visited[nbr]==false)
                if(dfsContainsCycle(nbr, visited, callStack, graph)) return true;
        }
        callStack[node] = false;
        return false;
    }
}
