//Used for longest strictly increasing paths, game routing, or constrained directional steps on 2D grids

public class Grid_DFS_DP_Template {
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int rows, cols;
    private int[][] memo;

    public int solveGrid(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        memo = new int[rows][cols];

        int maxResult = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                maxResult = Math.max(maxResult, dfs(grid, r, c));
            }
        }
        return maxResult;
    }

    private int dfs(int[][] grid, int r, int c) {
        if (memo[r][c] != 0) return memo[r][c];

        int ans = 1; // Base metric for cell itself
        for (int[] d : DIRS) {
            int nr = r + d[0], nc = c + d[1];

            // Transition condition (e.g., strictly increasing value)
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] > grid[r][c]) {
                ans = Math.max(ans, 1 + dfs(grid, nr, nc));
            }
        }
        return memo[r][c] = ans;
    }
}
