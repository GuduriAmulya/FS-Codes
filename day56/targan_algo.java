/*
/*
Hulk and Thor become best friends now. 
Hulk lives in the P-th house while Thor lives in the N-th house. 
Hulk has decided to meet Thor every day. After going for many days, 
he notices that there are few streets such that he passes every time 
he goes to meet Thor, no matter which path he takes.

You have been given a grid of N houses in the form of undirected graph. 
Houses are numbered from 1 to N.
For each I-th query, you have been given P (Hulk's house). 
You have to say how many streets are there which he encounters in every path 
he takes to go from P to N. If there is no such street, print "-1".
Also, note P can be equal to N.

Input Format:
-------------
Line-1: Two space separated integers N and M, Number of houses, number of streets.
Next M lines: contain two space-separated integers u and v, 
		denoting a street between house-u and house-v.
Next line: contains an integer P.

Output Format:
--------------
Print an integer, number of streets in that path.


Sample Input-1:
---------------
4 3
1 2
1 3
2 4
1

Sample Output-1:
----------------
2

Explanation:
------------
There is only one path 1->2->4. so answer = 2


Sample Input-2:
---------------
4 3
1 2
1 3
2 4
4

Sample Output-2:
----------------
-1

Explanation:
------------
There is no path as they both live in the same house 4.
So answer = -1.

*/
import java.util.*;

class Solution {
    static List<List<Integer>> adj;
    static int[] disc, low;
    static boolean[] vis;
    static int time = 0;
    static Set<String> bridges = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int p = sc.nextInt();
        int target = n;

        if (p == target) {
            System.out.println(-1);
            return;
        }

        disc = new int[n + 1];
        low = new int[n + 1];
        vis = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (!vis[i]) dfs(i, -1);
        }

        int ans = bfs(p, target);
        System.out.println(ans == 0 ? -1 : ans);
    }

    static void dfs(int u, int parent) {
        vis[u] = true;
        disc[u] = low[u] = ++time;

        for (int v : adj.get(u)) {
            if (v == parent) continue;

            if (!vis[v]) {
                dfs(v, u);
                low[u] = Math.min(low[u], low[v]);

                if (low[v] > disc[u]) {
                    bridges.add(u + "#" + v);
                    bridges.add(v + "#" + u);
                }
            } else {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    static int bfs(int start, int end) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] seen = new boolean[adj.size()];

        q.offer(new int[]{start, 0});
        seen[start] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0], cnt = cur[1];

            if (node == end) return cnt;

            for (int nei : adj.get(node)) {
                if (!seen[nei]) {
                    seen[nei] = true;
                    int nc = cnt;
                    if (bridges.contains(node + "#" + nei)) nc++;
                    q.offer(new int[]{nei, nc});
                }
            }
        }
        return -1;
    }
}
