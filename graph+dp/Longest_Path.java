import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void Longest_Path(String[] args) throws IOException {
        // Fast I/O for competitive programming constraints
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

        // dp[i] = length of the longest directed path ending at node i
        int[] dp = new int[n + 1];

        // Kahn's Algorithm initialization
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        int maxPathLength = 0;

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v : adj.get(u)) {
                // Transition: extending the path from u to v by 1 edge
                dp[v] = Math.max(dp[v], dp[u] + 1);
                maxPathLength = Math.max(maxPathLength, dp[v]);

                if (--inDegree[v] == 0) {
                    q.offer(v);
                }
            }
        }

        System.out.println(maxPathLength);
    }
}
