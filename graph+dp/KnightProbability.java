public class KnightProbability {
    private static final int[][] KNIGHT_MOVES = {
        {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
        {1, -2}, {1, 2}, {2, -1}, {2, 1}
    };

    private Double[][][] memo;

    public double knightProbability(int n, int k, int row, int column) {
        // memo[r][c][stepsLeft]
        memo = new Double[n][n][k + 1];
        return dfs(n, row, column, k);
    }

    private double dfs(int n, int r, int c, int stepsLeft) {
        // Base Case 1: Knight has fallen off the board
        if (r < 0 || r >= n || c < 0 || c >= n) {
            return 0.0;
        }

        // Base Case 2: All k moves executed successfully while on the board
        if (stepsLeft == 0) {
            return 1.0;
        }

        // Return memoized result if already computed
        if (memo[r][c][stepsLeft] != null) {
            return memo[r][c][stepsLeft];
        }

        double totalProb = 0.0;

        // Sum the branch probabilities for all 8 possible moves
        for (int[] move : KNIGHT_MOVES) {
            int nr = r + move[0];
            int nc = c + move[1];

            totalProb += dfs(n, nr, nc, stepsLeft - 1) / 8.0;
        }

        return memo[r][c][stepsLeft] = totalProb;
    }
}
