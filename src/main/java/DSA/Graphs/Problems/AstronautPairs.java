package DSA.Graphs.Problems;


/*
SpecialX has launched a special spacecraft program that is aimed at sending people into space. The spacecraft is designed to carry only two astronauts at a time. Melon Musk has an idea to sort things out and select who goes in which trip. He wants to choose the two astronauts from different countries. You will be given a list of pairs of astronaut IDs. Each pair is made of astronauts from the same country. Determine how many pairs of astronauts from different countries he can choose from.

Input Format

The first line contains two integers N and P, the number of astronauts and the number of pairs. Each of the next P lines contains 2 space-separated integers denoting astronaut IDs of two who share the same nationality.

Constraints

1 ≤ N ≤ 25,000
1 ≤ P ≤ 25,000
Output Format

An integer A that denotes the number of ways to choose a pair of astronauts from different coutries.

Sample Input 0

5 3
0 1
2 3
0 4
Sample Output 0

6
Explanation 0

Persons numbered [0,1,4] belong to one country, and those numbered [2,3] belong to another. Elon has 6 ways of choosing a pair: [0,2] , [0,3] , [1,2] , [1,3] , [4,2] , [4,3]

Sample Input 1

4 1
0 2
Sample Output 1

5
Explanation 1

Persons numbered [0,2] belong to the same country, but persons 1 and 3 don't share countries with anyone else. Elon has 5 ways of choosing a pair: [0,1] , [0,3] , [1,2] , [1,3] , [2,3]
0 2 , 1, 3
*/

import DSA.Graphs.GraphGen;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AstronautPairs {

    public static void main(String[] args) {
        GraphGen graph = new GraphGen();
        int n = 5;
        int [][] astMat =  {{0, 1}, {2, 3}, {0, 4}};
        for(int i=0;i<n; i++)
            graph.addVertex(i);
        for(int [] ast : astMat)
            graph.addEdge(ast[0], ast[1], false);
        List<Set<Integer>> comps = sizeOfConnectedComponentsOfGraph(graph, n);
        int[] sizes = new int[comps.size()];
        //total - number of connected components
        int total= n*(n-1)/2;
        for(Set comp : comps){
            int temp = comp.size();
            total = total - temp*(temp-1)/2;
        }
        System.out.println(total);
    }

    public static List<Set<Integer>> sizeOfConnectedComponentsOfGraph(GraphGen graph, int n){
        List<Set<Integer>> conn_comps = new ArrayList<>();
        for(int i =0; i <n ; i++){
            //for each i do the dfs if that node is not visited already
            boolean visited = false;
            int finalI = i;
            visited = conn_comps.stream().anyMatch(conn_comp -> conn_comp.contains(finalI));
            if(!visited)
                conn_comps.add(graph.dfs(i));
        }
        return conn_comps;
    }
}
