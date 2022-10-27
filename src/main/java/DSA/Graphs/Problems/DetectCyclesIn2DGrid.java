package DSA.Graphs.Problems;

import java.util.*;

public class DetectCyclesIn2DGrid {

    public static void main(String[] args) {

        char [][] grid = {{'c','a','d'},{'a','a','a'},{'a','a','d'},{'a','c','d'},{'a','b','c'}};
                //{{'a','b','b'},{'b','z','b'},{'b','b','a'}};
                //{{'a','a','a','a'},{'a','b','b','a'},{'a','b','b','a'},{'a','a','a','a'}};
        int n = grid.length;
        int m = grid[0].length;
        boolean visited[][] = new boolean[n][m];//0 - not visited , 1/2 visited with color
        boolean res = false;
        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                List<Integer> cur = List.of(i, j);
                if(!visited[i][j]){
                    res =  dfs(cur, List.of(-1, -1), grid, visited);
                    if(res == true){System.out.println(true); break;}
                }
            }
        }
        if(!res)
            System.out.println("False");
    }

    public static boolean dfs(List<Integer> cur_cord, List<Integer> parent_cord, char[][] grid, boolean[][] visited) {
        visited[cur_cord.get(0)][cur_cord.get(1)] = true;
        for (List<Integer> nbr_cor : getNbhrs(grid, cur_cord)) {
            if (visited[nbr_cor.get(0)][nbr_cor.get(1)]) {
                if (parent_cord.get(0)!=nbr_cor.get(0) && parent_cord.get(1)!=nbr_cor.get(1)) {
                    return true;
                }
            } else {
                if (dfs(nbr_cor, cur_cord, grid, visited))
                    return true;
            }
        }
        return false;
    }


    public static List<List<Integer>> getNbrs(char[][] grid , List<Integer> cur_cord) {
        int n = grid.length;
        int m = grid[0].length;
        char cur_val = grid[cur_cord.get(0)][cur_cord.get(1)];
        List<List<Integer>> res = new ArrayList<>();
        if (cur_cord.get(0) - 1 >= 0 && cur_val == grid[cur_cord.get(0)-1][cur_cord.get(1)]) {
            res.add(new ArrayList<>() {{
                add(cur_cord.get(0) - 1);
                add(cur_cord.get(1));
            }});
        }
        if (cur_cord.get(0) + 1 < n && cur_val == grid[cur_cord.get(0)+1][cur_cord.get(1)]) {
            res.add(new ArrayList<>() {{
                add(cur_cord.get(0) + 1);
                add(cur_cord.get(1));
            }});
        }
        if (cur_cord.get(1) - 1 >= 0 && cur_val == grid[cur_cord.get(0)][cur_cord.get(1)-1]) {
            res.add(new ArrayList<>() {{
                add(cur_cord.get(0));
                add(cur_cord.get(1) - 1);
            }});
        }
        if (cur_cord.get(1) + 1 < m && cur_val == grid[cur_cord.get(0)][cur_cord.get(1)+1]) {
            res.add(new ArrayList<>() {{
                add(cur_cord.get(0));
                add(cur_cord.get(1) + 1);
            }});
        }
        return res;
    }

    public static List<List<Integer>> getNbhrs(char[][] grid, List<Integer> cur_cord){
        List<List<Integer>> nbrs = new ArrayList<>();
        int n = grid.length;
        int m = grid[0].length;
        char cur_val = grid[cur_cord.get(0)][cur_cord.get(1)];
        int[] DIR_X = {1, -1, 0, 0};
        int[] DIR_Y = {0, 0, 1, -1};
        for (int i = 0; i < 4; i++) {
            int new_x = cur_cord.get(0)+DIR_X[i];
            int new_y = cur_cord.get(1)+DIR_Y[i];
            if(new_x >= 0 && new_x < n && new_y >= 0 && new_y < m && grid[new_x][new_y]==cur_val)
                nbrs.add( new ArrayList<>(){{ add(new_x); add(new_y); }});
        }
        return nbrs;
    }
}
