/*
Venkatadri is a maths teacher.
He is teaching matrices to his students.
He is given a matrix of size m*n, and it contains only positive numbers.
He has given a task to his students to find the special matrix, 
in the iven matrix A[m][n].
A special matrix has following property:
	- The sum of elements in each row, each column and the two diagonals are equal.
	- Every 1*1 matrix is called as a special matrix.
	- The size of the special matrix should be a square, i.e., P*P.

Your task is to help the students to find the speical matrix  with max size P.


Input Format:
-------------
Line-1: Two space separated integers M and N, size of the matrix.
Next M lines: N space separated integers m and n.

Output Format:
--------------
Print an integer, maximum size P of the special matrix.


Sample Input-1:
---------------
5 5
7 8 3 5 6
3 5 1 6 7
3 5 4 3 1
6 2 7 3 2
5 4 7 6 2

Sample Output-1:
----------------
3

Explanation:
------------
The special matrix is:
5 1 6
5 4 3
2 7 3


Sample Input-2:
---------------
4 4
7 8 3 5
3 2 1 6
3 2 3 3
6 2 3 3

Sample Output-2:
----------------
2

Explanation:
------------
The special matrix is:
3 3
3 3


*/

import java.util.*;

public class SpecialMatrix  {

    static boolean isSpecial(int[][] a, int r, int c, int k,
                             long[][] rowPrefix,
                             long[][] colPrefix,
                             long[][] diag1,
                             long[][] diag2) {

        long target = diag1[r + k][c + k] - diag1[r][c];

        long antiDiag = diag2[r + k][c + 1] - diag2[r][c + k + 1];
        if (antiDiag != target) return false;

        // Check all row sums
        for (int i = r; i < r + k; i++) {
            long rowSum = rowPrefix[i][c + k] - rowPrefix[i][c];
            if (rowSum != target) return false;
        }

        // Check all column sums
        for (int j = c; j < c + k; j++) {
            long colSum = colPrefix[r + k][j] - colPrefix[r][j];
            if (colSum != target) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] a = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
            }
        }

        // Row prefix sums
        long[][] rowPrefix = new long[m][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowPrefix[i][j + 1] = rowPrefix[i][j] + a[i][j];
            }
        }

        // Column prefix sums
        long[][] colPrefix = new long[m + 1][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                colPrefix[i + 1][j] = colPrefix[i][j] + a[i][j];
            }
        }

        // Main diagonal prefix
        long[][] diag1 = new long[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                diag1[i + 1][j + 1] = a[i][j] + diag1[i][j];
            }
        }

        // Anti-diagonal prefix
        long[][] diag2 = new long[m + 1][n + 2];
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                diag2[i + 1][j] = a[i][j] + diag2[i][j + 1];
            }
        }

        int maxSize = 1;
        int limit = Math.min(m, n);

        for (int k = limit; k >= 2; k--) {
            boolean found = false;

            for (int r = 0; r <= m - k && !found; r++) {
                for (int c = 0; c <= n - k; c++) {

                    if (isSpecial(a, r, c, k,
                            rowPrefix, colPrefix, diag1, diag2)) {
                        maxSize = k;
                        found = true;
                        break;
                    }
                }
            }

            if (found) break;
        }

        System.out.println(maxSize);
    }
}