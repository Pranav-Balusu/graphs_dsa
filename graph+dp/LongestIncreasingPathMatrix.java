//grid+dp+inc/dec constraint

public class LongestIncreasingPathMatrix {
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int rows, cols;
    private int[][] memo;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        rows = matrix.length;
        cols = matrix[0].length;
        memo = new int[rows][cols];

        int maxPath = 0;

        // Evaluate the longest path starting from every cell
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                maxPath = Math.max(maxPath, dfs(matrix, r, c));
            }
        }

        return maxPath;
    }

    private int dfs(int[][] matrix, int r, int c) {
        // Return memoized result if already computed
        if (memo[r][c] != 0) {
            return memo[r][c];
        }

        int maxLength = 1; // Base case: the cell itself

        for (int[] d : DIRS) {
            int nr = r + d[0];
            int nc = c + d[1];

            // Strict increasing condition guarantees DAG behavior
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && matrix[nr][nc] > matrix[r][c]) {
                maxLength = Math.max(maxLength, 1 + dfs(matrix, nr, nc));
            }
        }

        return memo[r][c] = maxLength;
    }
}
