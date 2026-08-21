
//Used for counting paths, finding longest/shortest paths, or computing reachable values in a DAG.
import java.util.*;

public class DAG_DP_Template {
    public static int[] longestPathDAG(int n, List<List<int[]>> adj) {
        int[] inDegree = new int[n];
        for (int u = 0; u < n; u++) {
            for (int[] edge : adj.get(u)) {
                inDegree[edge[0]]++;
            }
        }

        // Kahn's Algorithm for TopoSort
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) q.offer(i);
        }

        // dp[i] = longest distance/metric ending at or starting from node i
        int[] dp = new int[n];
        Arrays.fill(dp, 0); // Use Integer.MIN_VALUE if paths must start at a specific source

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int[] edge : adj.get(u)) {
                int v = edge[0];
                int weight = edge[1];

                // DP Transition
                dp[v] = Math.max(dp[v], dp[u] + weight);

                if (--inDegree[v] == 0) {
                    q.offer(v);
                }
            }
        }
        return dp;
    }
}
