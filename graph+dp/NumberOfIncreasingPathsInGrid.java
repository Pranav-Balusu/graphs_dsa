public class NumberOfIncreasingPathsInGrid {
    private static final int MOD = 1_000_000_007;
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int rows, cols;
    private int[][] memo;

    public int countPaths(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        memo = new int[rows][cols];

        long totalPaths = 0;

        // Sum the number of paths starting from each cell (r, c)
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                totalPaths = (totalPaths + dfs(grid, r, c)) % MOD;
            }
        }

        return (int) totalPaths;
    }

    private int dfs(int[][] grid, int r, int c) {
        // Return memoized result if already calculated
        if (memo[r][c] != 0) {
            return memo[r][c];
        }

        // Base case: The single-cell path [ (r, c) ]
        long count = 1;

        for (int[] d : DIRS) {
            int nr = r + d[0];
            int nc = c + d[1];

            // Valid strictly increasing step
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] > grid[r][c]) {
                count = (count + dfs(grid, nr, nc)) % MOD;
            }
        }

        return memo[r][c] = (int) count;
    }
}
