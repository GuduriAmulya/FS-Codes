//root with more than 1 children is articulation point..
// Enter number of vertices: 5
// Enter number of edges: 5

// 0 1
// 1 2
// 2 0
// 1 3
// 3 4
//op=> 1,3
//O(V+E)
import java.util.*;
class ArticulationPoint{
    static int time=0;
    public static void dfs(int u, int parent, boolean[]visited,int[]disc,int[]low,boolean[]isAP,ArrayList<ArrayList<Integer>>adj){
        int children=0;
        disc[u]=low[u]=++time;
        visited[u]=true;
        for(int v:adj.get(u)){
            if(v==parent)continue;
            if(!visited[v]){
                children++;
                dfs(v,u,visited,disc,low,isAP,adj);
                low[u]=Math.min(low[u],low[v]);
                if(parent!=-1 && low[v]>=disc[u]){
                    isAP[u]=true;//u is articulation..
                }
            }
            else{
                //already visted meaning -back edge;
                low[u]=Math.min(low[u],disc[v]);
            }
        }
        // Case 1: Root articulation point
        if (parent == -1 && children > 1) {
            isAP[u] = true;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
        boolean[] isAP = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, -1, visited, disc, low, isAP, adj);
            }
        }

        System.out.println("Articulation Points:");
        for (int i = 0; i < n; i++) {
            if (isAP[i]) {
                System.out.println(i);
            }
        }

    }
}