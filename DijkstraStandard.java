import java.util.*;

class DijkstraStandard {
    public int[] dijkstra(int n, List<List<int[]>> graph, int src) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{src, 0}); // {node, distFromSrc}

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int d = cur[1];

            if (d > dist[node]) continue; // Skip outdated entry

            for (int[] edge : graph.get(node)) {
                int nei = edge[0];
                int weight = edge[1];

                if (dist[node] + weight < dist[nei]) {
                    dist[nei] = dist[node] + weight;
                    pq.offer(new int[]{nei, dist[nei]});
                }
            }
        }

        return dist; // dist[i] = shortest distance from src to i
    }
}
