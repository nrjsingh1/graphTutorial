package DSA.Graphs;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@Slf4j
@SpringBootTest
public class GraphTest {

    @Autowired
    private Graph graph;


    @Test
    public void printGraphListTest(){
        graph = new Graph();
        graph.addVertex("Bob");
        graph.addVertex("Alice");
        graph.addVertex("Mark");
        graph.addVertex("Rob");
        graph.addVertex("Maria");
        graph.addEdge("Bob", "Alice");
        graph.addEdge("Bob", "Rob");
        graph.addEdge("Alice", "Mark");
        graph.addEdge("Rob", "Mark");
        graph.addEdge("Alice", "Maria");
        graph.addEdge("Rob", "Maria");
        graph.printGraphInList();
        graph.graphTraversal("Bob", TraversalType.BFS);
        graph.graphTraversal("Bob", TraversalType.DFS);
    }

    @Test
    public void findIfPathExists(){
        //https://leetcode.com/problems/find-if-path-exists-in-graph/
        //n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5 , op = false
        //10
        //[[4,3],[1,4],[4,8],[1,7],[6,4],[4,2],[7,4],[4,0],[0,9],[5,4]]
        //5
        //9
        //true
        //6
        //[[0,1],[0,2],[3,5],[5,4],[4,3]]
        //0
        //5
        //false
//        int n=7;
//        int[][] edges = {{0,1},{1,2},{2,3},{3,5},{5,6},{4,5},{0,4},{3,4}};
//        String src = "1";
//        String des = "5";
        int n=6;
        int[][] edges = {{0,1},{0,2},{3,5},{5,4},{4,3}};
        String src = "0";
        String des = "5";

        Graph graph = new Graph();
        for(int i=0; i<n ;i++)
            graph.addVertex(String.valueOf(i));

        for(int [] ed : edges)
            graph.addEdge(String.valueOf(ed[0]), String.valueOf(ed[1]));

        graph.printGraphInList();

        log.info("Path Exists From {} To {} : {}", src ,des, graph.ifDirectPathExists(src, des));

        graph.graphTraversal(src, TraversalType.BFS);

    }

    //https://leetcode.com/problems/snakes-and-ladders/
    @Test
    public void snakeANdLadderTest(){
        log.info("Moves {}", snakeLadderImpl2());
    }

    public void snakeAndLadderImpl1(){
        //int[][] board = {{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,35,-1,-1,13,-1},{-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}};
        //int[][] board = {{1,1,-1},{1,1,1},{-1,1,1}};
        //int[][] board = {{-1,-1,-1},{-1,9,8},{-1,8,9}};
        //int[][] board = {{1,1,-1},{1,1,1},{-1,1,1}};
        //int[][] board = {{-1,-1,2,1,57,7,20,-1},{1,-1,56,33,-1,50,48,-1},{-1,-1,58,36,-1,-1,-1,-1},{-1,44,41,17,25,29,-1,-1},{-1,37,54,64,46,34,-1,-1},{-1,45,29,24,-1,-1,22,46},{-1,17,4,56,-1,-1,-1,16},{-1,-1,40,35,7,34,-1,-1}};
        int[][] board = {{-1,-1,-1,135,-1,-1,-1,-1,-1,185,-1,-1,-1,-1,105,-1},{-1,-1,92,-1,-1,-1,-1,-1,-1,201,-1,118,-1,-1,183,-1},{-1,-1,-1,-1,-1,-1,-1,-1,-1,179,-1,-1,-1,-1,-1,-1},{-1,248,-1,-1,-1,-1,-1,-1,-1,119,-1,-1,-1,-1,-1,192},{-1,-1,104,-1,-1,-1,-1,-1,-1,-1,165,-1,-1,206,104,-1},{145,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,229,-1},{-1,-1,75,140,-1,-1,-1,-1,-1,-1,-1,-1,43,-1,34,-1},{-1,-1,-1,-1,-1,-1,169,-1,-1,-1,-1,-1,-1,188,-1,-1},{-1,-1,-1,-1,-1,-1,92,-1,171,-1,-1,-1,-1,-1,-1,66},{-1,-1,-1,126,-1,-1,68,-1,-1,-1,-1,-1,-1,-1,-1,-1},{-1,109,-1,86,28,228,-1,-1,144,-1,-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,59,-1,-1,-1,-1,-1,51,-1,-1,-1,62,-1},{-1,71,-1,-1,-1,63,-1,-1,-1,-1,-1,-1,212,-1,-1,-1},{-1,-1,-1,-1,174,-1,59,-1,-1,-1,-1,-1,-1,133,-1,-1},{-1,-1,62,-1,5,-1,16,-1,-1,-1,-1,-1,-1,-1,-1,-1},{-1,-1,-1,59,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}};
        int n =board.length;
        int[] map = new int[n*n+1];
        int nodesSeq =1;
        for(int i=n-1; i>=0; i--){
            if((n-i)%2==0){
                for(int j =n-1; j>=0; j--){
                    map[nodesSeq++]=board[i][j];
                }
            }
            else {
                for(int j =0; j<n; j++){
                    map[nodesSeq++]=board[i][j];
                }
            }
        }
        Graph graph = new Graph();
        for(int i =1; i <=n*n ; i++)
            graph.addVertex(String.valueOf(i));

        //add edge
        Set<Integer> tempV = new LinkedHashSet<>();
        tempV.add(1);
        for(int cur=1; cur<=n*n; cur++){
            if(!tempV.contains(cur))
                continue;
            for(int chc = cur+1; chc<= Math.min(cur+6, n*n); chc++){
                int next=0;
                if(map[chc]==-1){
                    next = chc;
                } else {
                    next = map[chc];
                }
                tempV.add(next);
                graph.addEdge(String.valueOf(cur), String.valueOf(next), true);
            }
        }
        log.info("Shortest moves to {} : {}", n*n , graph.getBfsSingleShortestDistance("1", String.valueOf(n*n)));
        log.info("Shortest Path from {} to {} : {}", 1, n*n, graph.getBfsSingleShortestPath("1", String.valueOf(n*n)));
    }


    private int getBoardValue(int[][] board, int num) {
        int n = board.length;
        int r = (num - 1) / n;
        int x = n - 1 - r;
        int y = r % 2 == 0 ? num - 1 - r * n : n + r * n - num;
        log.info(" x {} : y {}", x, y);
        return board[x][y];
    }

    @Test
    public void MessageRoute(){

        Graph graph = new Graph();
        int n = 5;
        for(int i =1; i<=n; i++)
            graph.addVertex(String.valueOf(i));

        int m = 5;
        int[][] edges = {{1,2},{1,3},{1,4},{2,3},{5,4}};
        for(int[] ed : edges)
            graph.addEdge(String.valueOf(ed[0]), String.valueOf(ed[1]));

        boolean ifPath = graph.ifDirectPathExists(String.valueOf(1), String.valueOf(n));
        if(ifPath){
            log.info("Shortest Distance :{}",graph.getBfsSingleShortestDistance(String.valueOf(1), String.valueOf(n)));
            log.info("Shortest Path:  {}", graph.getBfsSingleShortestPath(String.valueOf(1), String.valueOf(n)));
        }else{
            log.info("IMPOSSIBLE");
        }


    }

    @Test
    public void  test (){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("11");
        list.add("3");
        list.add("2");
        list.add("22");
        log.info("{}", list);
        Set<Integer> s = new HashSet<>();

    }

    public int snakeLadderImpl2() {
        int[][] board = {{-1, -1, -1, 135, -1, -1, -1, -1, -1, 185, -1, -1, -1, -1, 105, -1}, {-1, -1, 92, -1, -1, -1, -1, -1, -1, 201, -1, 118, -1, -1, 183, -1}, {-1, -1, -1, -1, -1, -1, -1, -1, -1, 179, -1, -1, -1, -1, -1, -1}, {-1, 248, -1, -1, -1, -1, -1, -1, -1, 119, -1, -1, -1, -1, -1, 192}, {-1, -1, 104, -1, -1, -1, -1, -1, -1, -1, 165, -1, -1, 206, 104, -1}, {145, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 229, -1}, {-1, -1, 75, 140, -1, -1, -1, -1, -1, -1, -1, -1, 43, -1, 34, -1}, {-1, -1, -1, -1, -1, -1, 169, -1, -1, -1, -1, -1, -1, 188, -1, -1}, {-1, -1, -1, -1, -1, -1, 92, -1, 171, -1, -1, -1, -1, -1, -1, 66}, {-1, -1, -1, 126, -1, -1, 68, -1, -1, -1, -1, -1, -1, -1, -1, -1}, {-1, 109, -1, 86, 28, 228, -1, -1, 144, -1, -1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, 59, -1, -1, -1, -1, -1, 51, -1, -1, -1, 62, -1}, {-1, 71, -1, -1, -1, 63, -1, -1, -1, -1, -1, -1, 212, -1, -1, -1}, {-1, -1, -1, -1, 174, -1, 59, -1, -1, -1, -1, -1, -1, 133, -1, -1}, {-1, -1, 62, -1, 5, -1, 16, -1, -1, -1, -1, -1, -1, -1, -1, -1}, {-1, -1, -1, 59, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}};

        int n = board.length;
        int[] map = new int[n * n + 1];
        int nodesSeq = 1;
        for (int i = n - 1; i >= 0; i--) {
            if ((n - i) % 2 == 0) {
                for (int j = n - 1; j >= 0; j--) {
                    map[nodesSeq++] = board[i][j];
                }
            } else {
                for (int j = 0; j < n; j++) {
                    map[nodesSeq++] = board[i][j];
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        boolean[] visited = new boolean[n * n + 1];
        for (int move = 0; !queue.isEmpty(); move++) {
            for (int size = queue.size(); size > 0; size--) {
                int num = queue.poll();
                if (visited[num]) continue;
                visited[num] = true;
                if (num == n * n) return move;
                for (int i = 1; i <= 6 && num + i <= n * n; i++) {
                    int next = num + i;
                    int value = map[next];
                    if (value > 0) next = value;
                    if (!visited[next]) queue.add(next);
                }
            }
        }
        return -1;
    }


}
