import java.util.*;

class SolutionKosaraju {
    public List<List<Integer>> kosarajuSCC(int n, List<List<Integer>> edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (List<Integer> edge : edges) {
            int u = edge.get(0), v = edge.get(1);
            graph.get(u).add(v);
        }

        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) dfs1(i, graph, visited, stack);
        }

        List<List<Integer>> revGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) revGraph.add(new ArrayList<>());
        for (int u = 0; u < n; u++) {
            for (int v : graph.get(u)) {
                revGraph.get(v).add(u);
            }
        }

        Arrays.fill(visited, false);
        List<List<Integer>> sccList = new ArrayList<>();
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                List<Integer> component = new ArrayList<>();
                dfs2(node, revGraph, visited, component);
                sccList.add(component);
            }
        }

        return sccList;
    }

    private void dfs1(int node, List<List<Integer>> graph, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int nei : graph.get(node)) {
            if (!visited[nei]) dfs1(nei, graph, visited, stack);
        }
        stack.push(node); 
    }

    private void dfs2(int node, List<List<Integer>> revGraph, boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node);
        for (int nei : revGraph.get(node)) {
            if (!visited[nei]) dfs2(nei, revGraph, visited, component);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<List<Integer>> edges = Arrays.asList(
            Arrays.asList(0, 1),
            Arrays.asList(1, 2),
            Arrays.asList(2, 0),
            Arrays.asList(1, 3)
        );

        List<List<Integer>> sccs = sol.kosarajuSCC(4, edges);
        System.out.println("Strongly Connected Components: " + sccs);
    }
}
