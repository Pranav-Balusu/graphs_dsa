
//Used for graphs with $N \le 20$ to track exact sets of visited nodes.
import java.util.Arrays;

public class Bitmask_Graph_DP_Template {
    private static final int INF = 1_000_000_000;

    public int tsp(int n, int[][] cost) {
        int totalStates = 1 << n;
        // dp[mask][u]: min cost to visit subset `mask` ending at node `u`
        int[][] dp = new int[totalStates][n];
        for (int[] row : dp) Arrays.fill(row, INF);

        // Base case: starting at node 0
        dp[1][0] = 0;

        for (int mask = 1; mask < totalStates; mask++) {
            for (int u = 0; u < n; u++) {
                if ((mask & (1 << u)) == 0 || dp[mask][u] == INF) continue;

                // Transition to next unvisited node v
                for (int v = 0; v < n; v++) {
                    if ((mask & (1 << v)) == 0) {
                        int nextMask = mask | (1 << v);
                        dp[nextMask][v] = Math.min(dp[nextMask][v], dp[mask][u] + cost[u][v]);
                    }
                }
            }
        }

        int minCost = INF;
        int allVisited = totalStates - 1;
        for (int u = 0; u < n; u++) {
            minCost = Math.min(minCost, dp[allVisited][u] + cost[u][0]); // Complete the cycle to 0
        }
        return minCost;
    }
}
