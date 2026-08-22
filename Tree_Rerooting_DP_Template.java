//Used for evaluating subtree properties across all possible choices of tree roots in $O(N)$ time.
//When solving a tree problem where an answer is required for every single node as if it were
//the root, the brute-force approach runs a full DFS/BFS for each node ($N$ runs $\implies O(N^2)$ time).

import java.util.*;

public class Tree_Rerooting_DP_Template {
    private List<List<Integer>> adj;
    private int[] count; // Subtree size
    private int[] ans;   // Result array for all roots
    private int n;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        this.n = n;
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        count = new int[n];
        ans = new int[n];

        // Pass 1: Bottom-up DP to compute values for default root (node 0)
        dfsBase(0, -1);

        // Pass 2: Top-down Re-rooting transition
        dfsReroot(0, -1);

        return ans;
    }

    private void dfsBase(int u, int parent) {
        count[u] = 1;
        for (int v : adj.get(u)) {
            if (v == parent) continue;
            dfsBase(v, u);
            count[u] += count[v];
            ans[0] += ans[v] + count[v]; // Metric accumulation
        }
    }

    private void dfsReroot(int u, int parent) {
        for (int v : adj.get(u)) {
            if (v == parent) continue;
            // Transition: shifting root from u to v
            ans[v] = ans[u] - count[v] + (n - count[v]);
            dfsReroot(v, u);
        }
    }
}
