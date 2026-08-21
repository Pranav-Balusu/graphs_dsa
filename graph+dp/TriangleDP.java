import java.util.List;

public class TriangleDP {
    private Integer[][] memo;
    private int n;

    public int minimumTotal(List<List<Integer>> triangle) {
        n = triangle.size();
        memo = new Integer[n][n];

        // Start from apex (0, 0)
        return dfs(triangle, 0, 0);
    }

    private int dfs(List<List<Integer>> triangle, int r, int c) {
        // Base Case: Reached the last row
        if (r == n - 1) {
            return triangle.get(r).get(c);
        }

        // Return memoized result if already computed
        if (memo[r][c] != null) {
            return memo[r][c];
        }

        // Two choices: go to (r+1, c) or (r+1, c+1)
        int down = dfs(triangle, r + 1, c);
        int downRight = dfs(triangle, r + 1, c + 1);

        int minNext = Math.min(down, downRight);

        return memo[r][c] = triangle.get(r).get(c) + minNext;
    }
}
