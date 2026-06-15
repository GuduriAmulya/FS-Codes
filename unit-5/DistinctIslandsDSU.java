import java.util.*;

class DSU {
    int[] parent;
    int[] size;

    DSU(int n) {
        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        parent[x] = find(parent[x]); // path compression
        return parent[x];
    }

    void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) {
            return;
        }

        // weighted union
        if (size[rootA] < size[rootB]) {
            parent[rootA] = rootB;
            size[rootB] += size[rootA];
        } else {
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }
    }
}

public class DistinctIslandsDSU {

    public static int countDistinctIslands(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        DSU dsu = new DSU(rows * cols);

        int[][] directions = {
                {0, 1},
                {1, 0}
        };

        // Build islands
        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < cols; c++) {

                if (grid[r][c] == 0) {
                    continue;
                }

                int current = r * cols + c;

                for (int[] d : directions) {

                    int nr = r + d[0];
                    int nc = c + d[1];

                    if (nr < rows &&
                        nc < cols &&
                        grid[nr][nc] == 1) {

                        int next = nr * cols + nc;

                        dsu.union(current, next);
                    }
                }
            }
        }

        // root → coordinates
        Map<Integer, List<int[]>> islands =
                new HashMap<>();

        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < cols; c++) {

                if (grid[r][c] == 1) {

                    int root =
                            dsu.find(r * cols + c);

                    islands
                        .computeIfAbsent(
                                root,
                                k -> new ArrayList<>()
                        )
                        .add(new int[]{r, c});
                }
            }
        }

        Set<String> distinct =
                new HashSet<>();

        // Normalize shapes
        for (List<int[]> cells :islands.values()) {

            Collections.sort(
                    cells,
                    (a, b) ->
                        a[0] == b[0]
                        ? a[1] - b[1]
                        : a[0] - b[0]
            );

            int baseR = cells.get(0)[0];
            int baseC = cells.get(0)[1];

            StringBuilder shape =
                    new StringBuilder();

            for (int[] cell : cells) {

                shape.append(
                        (cell[0] - baseR)
                        + ","
                        + (cell[1] - baseC)
                        + ";"
                );
            }

            distinct.add(
                    shape.toString()
            );
        }

        return distinct.size();
    }

    public static void main(String[] args) {

        int[][] grid = {
                {1,1,0,0,0},
                {1,0,0,1,1},
                {0,0,0,1,0},
                {1,1,0,0,0}
        };

        System.out.println(
                countDistinctIslands(grid)
        );
    }
}