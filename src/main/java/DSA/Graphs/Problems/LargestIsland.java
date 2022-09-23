package DSA.Graphs.Problems;

import java.util.*;

public class LargestIsland {
    public static void main(String[] args) {
        char [][] grid = {{'1','1','1'},{'0','1','0'},{'1','1','1'}};
//        for(int i =0; i<m; i++){
//            for(int j =0; j<n; j++){
//                grid[i][j]='1';
//            }
//        }
        for(int i: numIslands(grid))
            System.out.println(i);
    }

    public static List<Integer> numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Stack<List<Integer>> stack = new Stack<>();
        List<Integer> conn_counts = new ArrayList<>();
        Set<List<Integer>> visited = new HashSet<>();
        for(int i =0; i<m; i++){
            for(int j =0; j<n; j++){
                List<Integer> cur = List.of(i,j);
                if(grid[i][j]=='1' && !visited.contains(cur)){
                    stack.push(cur);
                    visited.add(cur);
                    int count =1;
                    while(!stack.isEmpty()){
                        List<List<Integer>> cur_nbrs = new ArrayList<>();
                        List<Integer> cur_c = stack.pop();
                        if(cur_c.get(0)-1>=0 && !visited.contains(List.of(cur_c.get(0)-1, cur_c.get(1))))
                            cur_nbrs.add(List.of(cur_c.get(0)-1, cur_c.get(1)));
                        if(cur_c.get(0)+1<m && !visited.contains(List.of(cur_c.get(0)+1, cur_c.get(1))))
                            cur_nbrs.add(List.of(cur_c.get(0)+1, cur_c.get(1)));
                        if(cur_c.get(1)-1>=0 && !visited.contains(List.of(cur_c.get(0), cur_c.get(1)-1)))
                            cur_nbrs.add(List.of(cur_c.get(0), cur_c.get(1)-1));
                        if(cur_c.get(1)+1<n && !visited.contains(List.of(cur_c.get(0), cur_c.get(1)+1)))
                            cur_nbrs.add(List.of(cur_c.get(0), cur_c.get(1)+1));

                        for( List<Integer> cur_nbr : cur_nbrs){
                            if(grid[cur_nbr.get(0)][cur_nbr.get(1)]=='1'){
                                stack.push(cur_nbr);
                                visited.add(cur_nbr);
                                count++;
                            }
                        }

                    }
                    conn_counts.add(count);
                }
            }
        }
        return conn_counts;
    }

}
