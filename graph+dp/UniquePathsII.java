import java.util.Arrays;

public class UniquePathsII {
    private int rows, cols;
    private int[][] memo;

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // If starting cell or destination cell is blocked
        if (obstacleGrid[0][0] == 1) return 0;

        this.rows = obstacleGrid.length;
        this.cols = obstacleGrid[0].length;
        this.memo = new int[rows][cols];

        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dfs(obstacleGrid, 0, 0);
    }

    private int dfs(int[][] grid, int r, int c) {
        // Boundary or obstacle check
        if (r >= rows || c >= cols || grid[r][c] == 1) {
            return 0;
        }

        // Base Case: Reached destination
        if (r == rows - 1 && c == cols - 1) {
            return 1;
        }

        // Return memoized subproblem result
        if (memo[r][c] != -1) {
            return memo[r][c];
        }

        // Move Down + Move Right
        int paths = dfs(grid, r + 1, c) + dfs(grid, r, c + 1);

        return memo[r][c] = paths;
    }
}
