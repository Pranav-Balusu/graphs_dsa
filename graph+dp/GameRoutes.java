import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class GameRoutes {
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        int[] inDegree = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            inDegree[v]++;
        }

        // Kahn's Algorithm initialization
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        // dp[i] = number of distinct paths from node 1 to node i
        int[] dp = new int[n + 1];
        dp[1] = 1; // 1 way to start at node 1

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v : adj.get(u)) {
                // Add paths accumulated at u to v
                dp[v] = (dp[v] + dp[u]) % MOD;

                if (--inDegree[v] == 0) {
                    q.offer(v);
                }
            }
        }

        System.out.println(dp[n]);
    }
}
