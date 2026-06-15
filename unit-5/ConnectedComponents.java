import java.util.*;

public class ConnectedComponents {
    static List<List<Integer>> buildAdj(int n, int m, Scanner sc) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        return adj;
    }

    static List<List<Integer>> componentsDFS(List<List<Integer>> adj) {
        int n = adj.size();
        boolean[] vis = new boolean[n];
        List<List<Integer>> comps = new ArrayList<>();
        for (int i = 0; i < n; i++) if (!vis[i]) {
            List<Integer> comp = new ArrayList<>();
            dfs(i, adj, vis, comp);
            comps.add(comp);
        }
        return comps;
    }

    static void dfs(int u, List<List<Integer>> adj, boolean[] vis, List<Integer> comp) {
        vis[u] = true;
        comp.add(u);
        for (int v : adj.get(u)) if (!vis[v]) dfs(v, adj, vis, comp);
    }

    static List<List<Integer>> componentsBFS(List<List<Integer>> adj) {
        int n = adj.size();
        boolean[] vis = new boolean[n];
        List<List<Integer>> comps = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) if (!vis[i]) {
            List<Integer> comp = new ArrayList<>();
            q.add(i); vis[i] = true;
            while (!q.isEmpty()) {
                int u = q.poll();
                comp.add(u);
                for (int v : adj.get(u)) if (!vis[v]) { vis[v] = true; q.add(v); }
            }
            comps.add(comp);
        }
        return comps;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Input: n m followed by m edges (0-indexed)
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<List<Integer>> adj = buildAdj(n, m, sc);

        List<List<Integer>> dfsComps = componentsDFS(adj);
        System.out.println("Connected components (DFS): " + dfsComps.size());
        for (List<Integer> c : dfsComps) System.out.println(c);

        List<List<Integer>> bfsComps = componentsBFS(adj);
        System.out.println("Connected components (BFS): " + bfsComps.size());
        for (List<Integer> c : bfsComps) System.out.println(c);
    }
}