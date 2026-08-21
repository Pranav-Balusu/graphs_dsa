public class KnightDialer {
    private static final int MOD = 1_000_000_007;
    private static final int[][] KNIGHT_MOVES = {
        {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
        {1, -2}, {1, 2}, {2, -1}, {2, 1}
    };

    private Integer[][][] memo;

    public int knightDialer(int n) {
        // memo[row][col][remainingSteps]
        memo = new Integer[4][3][n];

        long totalSequences = 0;

        // Sum sequences starting from each of the 10 valid digits
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                // Skip '*' at (3, 0) and '#' at (3, 2)
                if (isValidCell(r, c)) {
                    totalSequences = (totalSequences + dfs(r, c, n - 1)) % MOD;
                }
            }
        }

        return (int) totalSequences;
    }

    private int dfs(int r, int c, int remainingSteps) {
        // Base case: No more jumps left, valid length achieved
        if (remainingSteps == 0) {
            return 1;
        }

        // Return cached subproblem result
        if (memo[r][c][remainingSteps] != null) {
            return memo[r][c][remainingSteps];
        }

        long count = 0;

        for (int[] move : KNIGHT_MOVES) {
            int nr = r + move[0];
            int nc = c + move[1];

            // Only jump to valid digit cells on the dial pad
            if (isValidCell(nr, nc)) {
                count = (count + dfs(nr, nc, remainingSteps - 1)) % MOD;
            }
        }

        return memo[r][c][remainingSteps] = (int) count;
    }

    private boolean isValidCell(int r, int c) {
        // Boundary check
        if (r < 0 || r >= 4 || c < 0 || c >= 3) {
            return false;
        }
        // Exclude '*' at (3, 0) and '#' at (3, 2)
        if (r == 3 && (c == 0 || c == 2)) {
            return false;
        }
        return true;
    }
}
