public class MinimumFallingPathSumII {
    private int n;
    private Integer[][] memo;

    public int minFallingPathSum(int[][] grid) {
        n = grid.length;
        if (n == 1) return grid[0][0];

        memo = new Integer[n][n];

        int minPath = Integer.MAX_VALUE;

        // Try starting from every column in the first row
        for (int c = 0; c < n; c++) {
            minPath = Math.min(minPath, dfs(grid, 0, c));
        }

        return minPath;
    }

    private int dfs(int[][] grid, int r, int c) {
        // Base case: Reached the last row
        if (r == n - 1) {
            return grid[r][c];
        }

        // Return memoized result
        if (memo[r][c] != null) {
            return memo[r][c];
        }

        int minNext = Integer.MAX_VALUE;

        // Move to any column k in the next row where k != c
        for (int nextCol = 0; nextCol < n; nextCol++) {
            if (nextCol != c) {
                minNext = Math.min(minNext, dfs(grid, r + 1, nextCol));
            }
        }

        return memo[r][c] = grid[r][c] + minNext;
    }
}
