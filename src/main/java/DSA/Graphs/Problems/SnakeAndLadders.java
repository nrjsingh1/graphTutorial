package DSA.Graphs.Problems;

import java.util.LinkedList;
import java.util.Queue;

public class SnakeAndLadders {
    public int snakesAndLadders(int[][] board) {
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
