package DSA.Graphs.Problems;

public class MinimumDegreeConnectedTrio {
    public int minTrioDegree(int n, int[][] edges) {

        //load adjacency Matrix
        int[][] adjM = new int[n+1][n+1];
        //load degrees of nodes
        int[] deg = new int[n+1];

        for(int[] edge: edges){
            adjM[edge[0]][edge[1]] = adjM[edge[1]][edge[0]] =1 ;

            deg[edge[0]]++;
            deg[edge[1]]++;
        }
        int minDeg = Integer.MAX_VALUE;
        for(int i =1;i<=n; i++){
            for(int j=i+1 ; j<=n; j++){
                if(adjM[i][j]==1 && adjM[j][i]==1){
                    for(int k=j+1; k<=n ; k++){
                        if(adjM[i][k]==1 && adjM[k][i]==1
                                && adjM[j][k]==1 && adjM[k][j]==1){
                            minDeg = Math.min(minDeg, deg[i]+deg[j]+deg[k]-6);
                        }
                    }
                }
            }
        }
        if(minDeg == Integer.MAX_VALUE)
            return -1;
        else
            return minDeg;
    }
}
