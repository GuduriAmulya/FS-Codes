// Enter number of vertices: 5
// Enter number of edges: 5

// 0 1
// 1 2
// 2 0
// 1 3
// 3 4

import java.util.*;
class BridgesInGraph{
    static int time=0;
    public static void dfs(int u, int parent, boolean[]visited, int[]disc, int[]low,ArrayList<ArrayList<Integer>>adj){
        visited[u]=true;
        disc[u]=low[u]=++time;
        for(int v:adj.get(u)){
            if(v==parent)continue;
            if(!visited[v]){
                dfs(v,u,visited,disc,low,adj);
                low[u]=Math.min(low[u],low[v]);//upldate low[u] to min of low[u],low[v]
                if(low[v]>disc[u]){
                    System.out.println("Bridge: "+u+"-"+v);
                }
            }
            else{
                //if already visited meaning, there is a backedge from u to v
                low[u]=Math.min(low[u],disc[v]);
            }

        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int m = sc.nextInt();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        System.out.println("Enter edges (u v):");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u); // undirected graph
        }
        boolean[] visited = new boolean[n];
        int[] disc = new int[n];
        int[] low = new int[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, -1, visited, disc, low, adj);
            }
        }

    }
}