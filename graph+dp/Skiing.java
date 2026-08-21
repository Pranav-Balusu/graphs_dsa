import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Skiing {
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static int rows, cols;
    private static int[][] grid;
    private static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());

        grid = new int[rows][cols];
        memo = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < cols; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int maxSkiLength = 0;

        // Try starting at every possible cell to find the longest downhill run
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                maxSkiLength = Math.max(maxSkiLength, dfs(r, c));
            }
        }

        System.out.println(maxSkiLength);
    }

    private static int dfs(int r, int c) {
        // Return precalculated result if already visited
        if (memo[r][c] != 0) {
            return memo[r][c];
        }

        int maxLen = 1; // Base case: stopping at the current cell

        for (int[] d : DIRS) {
            int nr = r + d[0];
            int nc = c + d[1];

            // Can only ski downhill to strictly lower adjacent elevations
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] < grid[r][c]) {
                maxLen = Math.max(maxLen, 1 + dfs(nr, nc));
            }
        }

        return memo[r][c] = maxLen;
    }
}
