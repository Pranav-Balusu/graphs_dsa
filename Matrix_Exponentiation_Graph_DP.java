//Used for counting paths or finding optimal paths of exact length $K$ where $K \le 10^9$ and $N \le 100$.
public class Matrix_Exponentiation_Graph_DP {
    private static final long MOD = 1_000_000_007L;

    // Multiplies two N x N transition matrices
    public static long[][] multiply(long[][] A, long[][] B, int n) {
        long[][] C = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < n; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    // Computes A^power in O(N^3 * log(power))
    public static long[][] power(long[][] A, long exp, int n) {
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) res[i][i] = 1; // Identity matrix

        long[][] base = A;
        while (exp > 0) {
            if ((exp & 1) == 1) res = multiply(res, base, n);
            base = multiply(base, base, n);
            exp >>= 1;
        }
        return res;
    }

    // Number of paths from src to dest of length exact K
    public static long countPathsOfLengthK(long[][] adjMatrix, int n, long k, int src, int dest) {
        long[][] transitionK = power(adjMatrix, k, n);
        return transitionK[src][dest];
    }
}
