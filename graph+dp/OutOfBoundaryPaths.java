public class OutOfBoundaryPaths {
    private static final int MOD = 1_000_000_007;
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int rows, cols;
    private Integer[][][] memo;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        this.rows = m;
        this.cols = n;
        // memo[r][c][movesLeft]
        this.memo = new Integer[m][n][maxMove + 1];

        return dfs(startRow, startColumn, maxMove);
    }

    private int dfs(int r, int c, int movesLeft) {
        // Base Case 1: Successfully crossed boundary
        if (r < 0 || r >= rows || c < 0 || c >= cols) {
            return 1;
        }

        // Base Case 2: Exhausted all moves while still inside
        if (movesLeft == 0) {
            return 0;
        }

        // Return cached subproblem result
        if (memo[r][c][movesLeft] != null) {
            return memo[r][c][movesLeft];
        }

        long totalPaths = 0;

        for (int[] d : DIRS) {
            int nr = r + d[0];
            int nc = c + d[1];

            totalPaths = (totalPaths + dfs(nr, nc, movesLeft - 1)) % MOD;
        }

        return memo[r][c][movesLeft] = (int) totalPaths;
    }
}
