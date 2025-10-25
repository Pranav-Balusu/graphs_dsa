import java.util.*;

class Solution {
    private int time;
    private List<List<Integer>> graph;
    private int[] disc, low;
    private boolean[] visited, isArticulation;
    private List<List<Integer>> bridges;
    private int n;

    public void buildGraph(int n, List<List<Integer>> connections) {
        this.n = n;
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (List<Integer> conn : connections) {
            int u = conn.get(0), v = conn.get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
    }

    public Map<String, Object> tarjansAlgorithm(int n, List<List<Integer>> connections) {
        buildGraph(n, connections);

        disc = new int[n];
        low = new int[n];
        visited = new boolean[n];
        isArticulation = new boolean[n];
        bridges = new ArrayList<>();
        time = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, -1);
            }
        }

        List<Integer> articulationPoints = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (isArticulation[i]) articulationPoints.add(i);

        Map<String, Object> result = new HashMap<>();
        result.put("bridges", bridges);
        result.put("articulationPoints", articulationPoints);
        return result;
    }

    private void dfs(int u, int parent) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        int childCount = 0;

        for (int v : graph.get(u)) {
            if (v == parent) continue;

            if (!visited[v]) {
                childCount++;
                dfs(v, u);
                low[u] = Math.min(low[u], low[v]);

                // (u, v) is a bridge
                if (low[v] > disc[u]) {
                    bridges.add(Arrays.asList(u, v));
                }

                // u is articulation point (non-root)
                if (parent != -1 && low[v] >= disc[u]) {
                    isArticulation[u] = true;
                }
            } else {
                // Back edge
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        // Root articulation point
        if (parent == -1 && childCount > 1) {
            isArticulation[u] = true;
        }
    }

    // Example main() for testing
    public static void main(String[] args) {
        Solution sol = new Solution();
        List<List<Integer>> edges = Arrays.asList(
                Arrays.asList(0, 1),
                Arrays.asList(1, 2),
                Arrays.asList(2, 0),
                Arrays.asList(1, 3)
        );

        Map<String, Object> result = sol.tarjansAlgorithm(4, edges);
        System.out.println("Bridges: " + result.get("bridges"));
        System.out.println("Articulation Points: " + result.get("articulationPoints"));
    }
}
