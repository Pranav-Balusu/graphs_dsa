class Solution {
    static final int INF = (int)1e9;

    public int[] bellmanFord(int n, int[][] edges, int src) {
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        // Step 1: Relax all edges (n-1) times
        for (int i = 0; i < n - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], wt = edge[2];
                if (dist[u] != INF && dist[v] > dist[u] + wt) {
                    dist[v] = dist[u] + wt;
                }
            }
        }

        // Step 2: Detect negative weight cycle
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], wt = edge[2];
            if (dist[u] != INF && dist[v] > dist[u] + wt) {
                throw new RuntimeException("Negative cycle detected");
            }
        }

        return dist;
    }
}
