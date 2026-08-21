import java.util.Arrays;

public class MinimumPathSum {
    private int rows, cols;
    private int[][] memo;

    public int minPathSum(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        memo = new int[rows][cols];

        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dfs(grid, 0, 0);
    }

    private int dfs(int[][] grid, int r, int c) {
        // Base case: Reached the destination
        if (r == rows - 1 && c == cols - 1) {
            return grid[r][c];
        }

        // Return cached value if already computed
        if (memo[r][c] != -1) {
            return memo[r][c];
        }

        int minNext = Integer.MAX_VALUE;

        // Move Down
        if (r + 1 < rows) {
            minNext = Math.min(minNext, dfs(grid, r + 1, c));
        }

        // Move Right
        if (c + 1 < cols) {
            minNext = Math.min(minNext, dfs(grid, r, c + 1));
        }

        return memo[r][c] = grid[r][c] + minNext;
    }
}
