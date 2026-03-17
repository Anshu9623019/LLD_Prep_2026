package DSA.Graph;

import java.util.*;

public class DisjointSetMain {

    public static void main(String[] args) {

    }


    //Prism Algorithm
    static class Pair4 {
        int node, weight;

        Pair4(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public class MST {

        public static int prims(int V, ArrayList<ArrayList<GraphReprasentation.Solution.Pair4>> adj) {

            boolean[] vis = new boolean[V];

            PriorityQueue<GraphReprasentation.Solution.Pair4> pq =
                    new PriorityQueue<>((a, b) -> a.weight - b.weight);

            pq.add(new GraphReprasentation.Solution.Pair4(0, 0));

            int sum = 0;

            while (!pq.isEmpty()) {

                GraphReprasentation.Solution.Pair4 curr = pq.poll();

                int node = curr.node;
                int wt = curr.weight;

                if (vis[node]) continue;

                vis[node] = true;

                sum += wt;

                for (GraphReprasentation.Solution.Pair4 p : adj.get(node)) {

                    if (!vis[p.node])
                        pq.add(new GraphReprasentation.Solution.Pair4(p.node, p.weight));
                }
            }

            return sum;
        }

    }

       static class DisjointSet {

            int parent[];
            int rank[];

            DisjointSet(int n){

                parent = new int[n];
                rank = new int[n];

                for(int i=0;i<n;i++)
                    parent[i] = i;
            }

            public int find(int node){

                if(parent[node] == node)
                    return node;

                return parent[node] = find(parent[node]);
            }

            public void union(int u,int v){

                int pu = find(u);
                int pv = find(v);

                if(pu == pv) return;

                if(rank[pu] < rank[pv]){
                    parent[pu] = pv;
                }
                else if(rank[pv] < rank[pu]){
                    parent[pv] = pu;
                }
                else{
                    parent[pv] = pu;
                    rank[pu]++;
                }
            }
        }

        //CycleDetection using disjoint set
        public boolean hasCycle(int V, int[][] edges){

            DisjointSet ds = new DisjointSet(V);

            for(int[] e : edges){

                int u = e[0];
                int v = e[1];

                if(ds.find(u) == ds.find(v))
                    return true;

                ds.union(u,v);
            }

            return false;
        }

        //Kruskal Algo
        class Edge{
            int u,v,wt;

            Edge(int u,int v,int wt){
                this.u=u;
                this.v=v;
                this.wt=wt;
            }
        }

        public int kruskalMST(int V, List<Edge> edges){

            Collections.sort(edges,(a, b)->a.wt-b.wt);

            DisjointSet ds = new DisjointSet(V);

            int mstWeight = 0;

            for(Edge e : edges){

                int u = e.u;
                int v = e.v;
                int wt = e.wt;

                if(ds.find(u) != ds.find(v)){

                    mstWeight += wt;

                    ds.union(u,v);
                }
            }

            return mstWeight;
        }

        //Number of provinces using disjoijt set
        public static int numberOfProvinces(int[][] isConnected){

            int n = isConnected.length;

            DisjointSet ds = new DisjointSet(n);

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){

                    if(isConnected[i][j] == 1){
                        ds.union(i,j);
                    }
                }
            }

            //Optimise loop as isConnected[i][j] == isConnected[j][i]
//                for(int i=0;i<n;i++){
//                    for(int j=i+1;j<n;j++){
//                        if(isConnected[i][j] == 1){
//                            ds.union(i,j);
//                        }
//                    }
//                }

            int count = 0;

            for(int i=0;i<n;i++){
                if(ds.find(i) == i)
                    count++;
            }

            return count;
        }

        //min no of operation to make graph connected
        public int makeConnected(int n, int[][] connections) {

            if(connections.length < n-1)
                return -1;

            DisjointSet ds = new DisjointSet(n);

            for(int[] edge : connections){

                int u = edge[0];
                int v = edge[1];

                ds.union(u,v);
            }

            int components = 0;

            for(int i=0;i<n;i++){
                if(ds.find(i) == i)
                    components++;
            }

            return components - 1;
        }

        //Account Merge
        public List<List<String>> accountsMerge(List<List<String>> accounts) {

            int n = accounts.size();

            DisjointSet ds = new DisjointSet(n);

            Map<String,Integer> emailMap = new HashMap<>();

            for(int i=0;i<n;i++){

                List<String> account = accounts.get(i);

                for(int j=1;j<account.size();j++){

                    String email = account.get(j);

                    if(!emailMap.containsKey(email)){
                        emailMap.put(email,i);
                    }
                    else{
                        ds.union(i,emailMap.get(email));
                    }
                }
            }

            Map<Integer,TreeSet<String>> merged = new HashMap<>();

            for(String email : emailMap.keySet()){

                int parent = ds.find(emailMap.get(email));

                merged.putIfAbsent(parent,new TreeSet<>());

                merged.get(parent).add(email);
            }

            List<List<String>> result = new ArrayList<>();

            for(int parent : merged.keySet()){

                List<String> temp = new ArrayList<>();

                temp.add(accounts.get(parent).get(0));

                temp.addAll(merged.get(parent));

                result.add(temp);
            }

            return result;
        }


        //Number of island 2
        public List<Integer> numIslands2(int m, int n, int[][] operators) {

            DisjointSet ds = new DisjointSet(m*n);

            int vis[][] = new int[m][n];

            List<Integer> ans = new ArrayList<>();

            int count = 0;

            int dr[] = {-1,1,0,0};
            int dc[] = {0,0,-1,1};

            for(int[] op : operators){

                int r = op[0];
                int c = op[1];

                if(vis[r][c] == 1){
                    ans.add(count);
                    continue;
                }

                vis[r][c] = 1;
                count++;

                int node = r*n + c;

                for(int k=0;k<4;k++){

                    int nr = r + dr[k];
                    int nc = c + dc[k];

                    if(nr>=0 && nc>=0 && nr<m && nc<n && vis[nr][nc]==1){

                        int adjNode = nr*n + nc;

                        if(ds.find(node) != ds.find(adjNode)){

                            count--;
                            ds.union(node,adjNode);
                        }
                    }
                }

                ans.add(count);
            }

            return ans;
        }

        //Max Island size
        static class DisjointSet2 {

            int parent[];
            int size[];

            DisjointSet2(int n){

                parent = new int[n];
                size = new int[n];

                for(int i=0;i<n;i++){
                    parent[i] = i;
                    size[i] = 1;
                }
            }

            public int find(int node){

                if(parent[node]==node)
                    return node;

                return parent[node] = find(parent[node]);
            }

            public void union(int u,int v){

                int pu = find(u);
                int pv = find(v);

                if(pu==pv) return;

                if(size[pu] < size[pv]){
                    parent[pu] = pv;
                    size[pv] += size[pu];
                }
                else{
                    parent[pv] = pu;
                    size[pu] += size[pv];
                }
            }
        }

        public int largestIsland(int[][] grid) {

            int n = grid.length;

            DisjointSet2 ds = new DisjointSet2(n*n);

            int dr[] = {-1,1,0,0};
            int dc[] = {0,0,-1,1};

            // Step 1: connect existing islands

            for(int r=0;r<n;r++){
                for(int c=0;c<n;c++){

                    if(grid[r][c]==0) continue;

                    for(int k=0;k<4;k++){

                        int nr = r + dr[k];
                        int nc = c + dc[k];

                        if(nr>=0 && nc>=0 && nr<n && nc<n && grid[nr][nc]==1){

                            int node = r*n + c;
                            int adj = nr*n + nc;

                            ds.union(node,adj);
                        }
                    }
                }
            }

            int max = 0;

            // Step 2: try converting each zero

            for(int r=0;r<n;r++){
                for(int c=0;c<n;c++){

                    if(grid[r][c]==1) continue;

                    HashSet<Integer> set = new HashSet<>();

                    for(int k=0;k<4;k++){

                        int nr = r + dr[k];
                        int nc = c + dc[k];

                        if(nr>=0 && nc>=0 && nr<n && nc<n && grid[nr][nc]==1){

                            int parent = ds.find(nr*n + nc);

                            set.add(parent);
                        }
                    }

                    int sizeTotal = 1;

                    for(int parent : set){
                        sizeTotal += ds.size[parent];
                    }

                    max = Math.max(max,sizeTotal);
                }
            }

            // case when grid already full of 1

            for(int i=0;i<n*n;i++){
                max = Math.max(max,ds.size[ds.find(i)]);
            }

            return max;
        }


        // Most Stone removed with same row and column : VVI
        public int removeStones(int[][] stones) {

            int maxRow = 0;
            int maxCol = 0;

            for(int[] s : stones){
                maxRow = Math.max(maxRow,s[0]);
                maxCol = Math.max(maxCol,s[1]);
            }

            DisjointSet ds = new DisjointSet(maxRow + maxCol + 2);

            HashSet<Integer> nodes = new HashSet<>();

            for(int[] s : stones){

                int row = s[0];
                int col = s[1] + maxRow + 1;

                ds.union(row,col);

                nodes.add(row);
                nodes.add(col);
            }

            int components = 0;

            for(int node : nodes){
                if(ds.find(node) == node)
                    components++;
            }

            return stones.length - components;
        }


}
