
//Used when distance depends on secondary constraints (e.g., fuel left, coupons used, time elapsed).
import java.util.*;

public class State_Dijkstra_DP_Template {
    static class State implements Comparable<State> {
        int u, k;
        long dist;

        State(int u, int k, long dist) {
            this.u = u;
            this.k = k;
            this.dist = dist;
        }

        @Override
        public int compareTo(State o) {
            return Long.compare(this.dist, o.dist);
        }
    }

    public long shortestPathWithKDiscount(int n, List<List<int[]>> adj, int maxK, int src, int dest) {
        // dist[u][k] = minimum cost to reach node u with k states/coupons used
        long[][] dist = new long[n][maxK + 1];
        for (long[] row : dist) Arrays.fill(row, Long.MAX_VALUE);

        PriorityQueue<State> pq = new PriorityQueue<>();
        dist[src][0] = 0;
        pq.offer(new State(src, 0, 0));

        while (!pq.isEmpty()) {
            State curr = pq.poll();
            int u = curr.u, k = curr.k;
            long d = curr.dist;

            if (d > dist[u][k]) continue;
            if (u == dest) return d;

            for (int[] edge : adj.get(u)) {
                int v = edge[0];
                int weight = edge[1];

                // Option 1: Standard edge transition (k remains unchanged)
                if (dist[u][k] + weight < dist[v][k]) {
                    dist[v][k] = dist[u][k] + weight;
                    pq.offer(new State(v, k, dist[v][k]));
                }

                // Option 2: State-modifying edge transition (e.g., applying half-price coupon)
                if (k < maxK && dist[u][k] + (weight / 2) < dist[v][k + 1]) {
                    dist[v][k + 1] = dist[u][k] + (weight / 2);
                    pq.offer(new State(v, k + 1, dist[v][k + 1]));
                }
            }
        }
        return -1;
    }
}
