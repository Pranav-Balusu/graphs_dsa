public class ValleyPathsGrid {
    private static final int MOD = 1_000_000_007;
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int rows, cols;
    private int[][] memo;

    public int countValleyPaths(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        rows = grid.length;
        cols = grid[0].length;
        memo = new int[rows][cols];

        long totalPaths = 0;

        // Sum downhill valley paths starting from every coordinate
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                totalPaths = (totalPaths + dfs(grid, r, c)) % MOD;
            }
        }

        return (int) totalPaths;
    }

    private int dfs(int[][] grid, int r, int c) {
        // Return cached result if already computed
        if (memo[r][c] != 0) {
            return memo[r][c];
        }

        long ways = 0;
        boolean hasLowerNeighbor = false;

        for (int[] d : DIRS) {
            int nr = r + d[0];
            int nc = c + d[1];

            // Valid strictly downhill step
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] < grid[r][c]) {
                hasLowerNeighbor = true;
                ways = (ways + dfs(grid, nr, nc)) % MOD;
            }
        }

        // If no lower neighbor exists, this cell is a valley sink (base path of length 1)
        if (!hasLowerNeighbor) {
            ways = 1;
        }

        return memo[r][c] = (int) ways;
    }
}
