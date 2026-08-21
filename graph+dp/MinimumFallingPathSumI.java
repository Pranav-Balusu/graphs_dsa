import java.util.Arrays;

public class MinimumFallingPathSumI {
    private int n;
    private Integer[][] memo;

    public int minFallingPathSum(int[][] matrix) {
        n = matrix.length;
        memo = new Integer[n][n];

        int minPath = Integer.MAX_VALUE;

        // Try starting from every column in the first row
        for (int c = 0; c < n; c++) {
            minPath = Math.min(minPath, dfs(matrix, 0, c));
        }

        return minPath;
    }

    private int dfs(int[][] matrix, int r, int c) {
        // Out of bounds column
        if (c < 0 || c >= n) {
            return Integer.MAX_VALUE;
        }

        // Base case: Reached the last row
        if (r == n - 1) {
            return matrix[r][c];
        }

        // Return memoized result
        if (memo[r][c] != null) {
            return memo[r][c];
        }

        // 3 valid moves down: (r+1, c-1), (r+1, c), (r+1, c+1)
        int downLeft = dfs(matrix, r + 1, c - 1);
        int down = dfs(matrix, r + 1, c);
        int downRight = dfs(matrix, r + 1, c + 1);

        int minNext = Math.min(down, Math.min(downLeft, downRight));

        return memo[r][c] = matrix[r][c] + minNext;
    }
}
