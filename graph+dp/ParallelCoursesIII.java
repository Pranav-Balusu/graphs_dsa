import java.util.*;

public class ParallelCoursesIII {
    public int minimumTime(int n, int[][] relations, int[] time) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        int[] inDegree = new int[n + 1];
        for (int[] rel : relations) {
            int u = rel[0];
            int v = rel[1];
            adj.get(u).add(v);
            inDegree[v]++;
        }

        // dp[i] = completion time of task i
        int[] dp = new int[n + 1];
        Queue<Integer> q = new ArrayDeque<>();

        // Initialize tasks with zero prerequisites
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                dp[i] = time[i - 1];
                q.offer(i);
            }
        }

        int minTotalTime = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            minTotalTime = Math.max(minTotalTime, dp[u]);

            for (int v : adj.get(u)) {
                // Task v cannot finish before task u finishes + its own time
                dp[v] = Math.max(dp[v], dp[u] + time[v - 1]);

                if (--inDegree[v] == 0) {
                    q.offer(v);
                }
            }
        }

        return minTotalTime;
    }
}
