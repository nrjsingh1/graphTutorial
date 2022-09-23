package DSA.Graphs.Problems;

import java.util.*;

public class MessageRoute {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        String temp = scn.nextLine();
        String[] arrS = temp.split(" ");
        int n = Integer.valueOf(arrS[0]);
        int m = Integer.valueOf(arrS[1]);

        int[][] conns = new int[m][2];
        for (int i = 0; i < m; i++) {
            String temp2 = scn.nextLine();
            String[] arrS2 = temp2.split(" ");
            conns[i][0] = Integer.valueOf(arrS2[0]);
            conns[i][1] = Integer.valueOf(arrS2[1]);
        }
        String path = messageRoute(n, m, conns);
        if (path != null) {
            System.out.println(path.split(" ").length);
            System.out.println(path);
        } else
            System.out.println("IMPOSSIBLE");
    }

    public static String messageRoute(int n, int m, int[][] conns) {
        ArrayList<HashSet<Integer>> graph = new ArrayList<HashSet<Integer>>();
        for (int i = 0; i <= n; i++)
            graph.add(i, new HashSet<>());
        for (int[] conn : conns) {
            graph.get(conn[0]).add(conn[1]);
            graph.get(conn[1]).add(conn[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        Set<Integer> visited = new LinkedHashSet<>();
        visited.add(1);
        String[] path = new String[n + 1];
        path[1] = "1";
        while (!queue.isEmpty()) {
            int ver = queue.poll();
            for (int vi : graph.get(ver)) {
                if (!visited.contains(vi)) {
                    visited.add(vi);
                    path[vi] = path[ver] + " " + vi;
                    queue.add(vi);
                }
            }
        }
        return path[n];
    }

}