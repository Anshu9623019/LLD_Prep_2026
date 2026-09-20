package DSA.Graph;

import java.util.*;

public class BFSTraversal {

    public static void main(String[] args) {

    }


    static public ArrayList<Integer> BFSTraversal(LinkedList<Integer> gp[]){
        int size = gp.length;
        int vis[] = new int[size];

        Queue<Integer> qe = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        qe.add(1);
        vis[1] = 1;
        while (!qe.isEmpty()){
            int curr = qe.poll();
            ans.add(curr);
            for(int i=0;i<gp[curr].size();i++){
                int temp = gp[curr].get(i);
                if(vis[temp]!=1){
                    qe.add(temp);
                    vis[temp] = 1;
                }
            }
        }
        return ans;
    }

    // Using BFS traversal : No of Island
    static  class Pair1{
        int r;
        int c;
        Pair1(int r,int c){
            this.r = r;
            this.c = c;
        }
    }

    public static int noOfIslandBfs(int mt[][]){
        int cnt = 0;
        int m = mt.length;
        int n = mt.length;
        int vis[][] = new int[m][n];

        for(int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if(vis[i][j]!=1 && mt[i][j]==1){
                    cnt++;
                    bfs(mt,vis,i,j);
                }
            }
        }
        return cnt;
    }

    public static void bfs(int mt[][],int vis[][],int i,int j){
        int r[] = {1,-1,0,0};
        int c[] = {0,0,1,-1};
        Queue<Pair1> qe = new LinkedList<>();
        qe.add(new Pair1(i,j));
        vis[i][j] = 1;
        while (!qe.isEmpty()){
            Pair1 p = qe.poll();
            int r1 = p.r;
            int c1 = p.c;
            for (int k=0;k<4;k++){
                int r2 = r1+r[k];
                int c2 = c1 + c[k];
                if(r2<mt.length && r2>=0 && c2<mt.length && c2>=0 && vis[r2][c2]!=1 && mt[r2][c2]==1){
                    vis[r2][c2] = 1;
                    qe.add(new Pair1(r2,c2));
                }
            }
        }

    }

    public static int rottenOranges(int img[][],int sr,int sc){
        int r[] = {0,0,-1,1};
        int c[] = {-1,1,0,0};
        int m = img.length;
        int n = img[0].length;
        Queue<Pair1> qe = new LinkedList<>();
        qe.add(new Pair1(sr,sc));
        int ans = 0;
        while (!qe.isEmpty()){
            int size = qe.size();
            ans++;
            for(int i=0;i<size;i++){
                Pair1 p = qe.poll();
                int r1 = p.r;
                int c1 = p.c;
                for(int k=0;k<4;k++){
                    int r2 = r1 + r[k];
                    int c2 = c1 + c[k];
                    if(r2<m && r2>=0 && c2<n && c2>=0 && img[r2][c2]==1){
                        qe.add(new Pair1(r2,c2));
                        img[r2][c2] = 2;
                    }
                }

            }
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(img[i][j]==1){
                    return -1;
                }
            }
        }
        return ans;
    }

    // Min distance from 1 node to another.

    static class Pair2 {
        int r, c;
        Pair2(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static int[][] nearestOne(int[][] grid){

        int m = grid.length;
        int n = grid[0].length;

        int[][] dist = new int[m][n];
        int[][] visited = new int[m][n];

        Queue<Pair2> queue = new LinkedList<>();

        // Step 1: Add all 1s to queue
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){

                if(grid[i][j] == 1){
                    queue.add(new Pair2(i, j));
                    visited[i][j] = 1;
                    dist[i][j] = 0;
                }
            }
        }

        int[] row = {1, -1, 0, 0};
        int[] col = {0, 0, 1, -1};

        // Step 2: BFS
        while(!queue.isEmpty()){

            Pair2 p = queue.poll();

            for(int k = 0; k < 4; k++){

                int newR = p.r + row[k];
                int newC = p.c + col[k];

                if(newR >= 0 && newR < m &&
                        newC >= 0 && newC < n &&
                        visited[newR][newC] == 0){

                    visited[newR][newC] = 1;
                    dist[newR][newC] = dist[p.r][p.c] + 1;

                    queue.add(new Pair2(newR, newC));
                }
            }
        }

        return dist;
    }

    //Shortest Path in undirected graph Using BFS
    public int[] shortestPath1(int N, int[][] edges, int src) {

        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        // Build undirected graph
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        int[] dist = new int[N];
        Arrays.fill(dist, -1);  // -1 means not visited

        Queue<Integer> q = new LinkedList<>();

        q.add(src);
        dist[src] = 0;

        while (!q.isEmpty()) {

            int node = q.poll();

            for (int neighbor : adj.get(node)) {
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[node] + 1;
                    q.add(neighbor);
                }
            }
        }

        return dist;
    }
}
