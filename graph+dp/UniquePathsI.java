public class UniquePathsI {
    private int rows, cols;
    private int[][] memo;

    public int uniquePaths(int m, int n) {
        this.rows = m;
        this.cols = n;
        this.memo = new int[m][n];
        return dfs(0, 0);
    }

    private int dfs(int r, int c) {
        // Base Case: Reached destination
        if (r == rows - 1 && c == cols - 1) {
            return 1;
        }

        // Return memoized subproblem result
        if (memo[r][c] != 0) {
            return memo[r][c];
        }

        int paths = 0;

        // Move Down
        if (r + 1 < rows) {
            paths += dfs(r + 1, c);
        }

        // Move Right
        if (c + 1 < cols) {
            paths += dfs(r, c + 1);
        }

        return memo[r][c] = paths;
    }
}
