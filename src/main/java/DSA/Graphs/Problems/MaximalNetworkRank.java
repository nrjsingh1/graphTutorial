package DSA.Graphs.Problems;

public class MaximalNetworkRank {


    public int maximalNetworkRank(int n, int[][] roads) {

        if (roads.length <= 0) return 0;

        if (n <= 2) return 1;

        int[] counts = new int[n];
        for (int[] rd : roads) {
            counts[rd[0]]++;
            counts[rd[1]]++;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                boolean edExist = false;
                for (int[] ed : roads)
                    if (i == ed[0] && j == ed[1] || i == ed[1] && j == ed[0]) {
                        edExist = true;
                        break;
                    }
                int tmax = 0;
                if (edExist) tmax = counts[i] + counts[j] - 1;
                else tmax = counts[i] + counts[j];
                max = Math.max(max, tmax);
            }
        }
        return max;
    }
}
