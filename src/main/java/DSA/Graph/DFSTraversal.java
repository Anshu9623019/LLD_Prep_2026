package DSA.Graph;

import java.util.*;

public class DFSTraversal {
    public static void main(String[] args) {

    }

    public static ArrayList<Integer> dfsFull(LinkedList<Integer>[] gp) {

        int n = gp.length;
        boolean[] visited = new boolean[n];
        ArrayList<Integer> ans = new ArrayList<>();

        for(int i = 1; i < n; i++){
            if(!visited[i]){
                dfsHelper(gp, visited, i, ans);
            }
        }

        return ans;
    }

    private static void dfsHelper(LinkedList<Integer>[] gp,
                                  boolean[] visited,
                                  int node,
                                  ArrayList<Integer> ans){
        visited[node] = true;
        ans.add(node);

        for(int neighbor : gp[node]){
            if(!visited[neighbor]){
                dfsHelper(gp, visited, neighbor, ans);
            }
        }
    }

    //Number of province, No of disconnect graph
    public static int noOfProvinces(int mt[][]){
        int cnt = 0;
        int v = mt.length;
           boolean vis[] = new boolean[v];
        for(int i=1;i<v;i++){
            if (!vis[i]) {
                cnt++;
                dfsHelper1(mt,vis,i);
            }
        }
        return cnt;
    }

    public static void dfsHelper1(int mt[][],boolean vis[],int j){
        vis[j] = true;
        for(int i=0;i<mt.length;i++){
            if(mt[j][i]==1 && !vis[i]) {
                dfsHelper1(mt,vis,i);
            }
        }
    }

    //Flood Fill Algorithm : Choose Any DFS or BFS
    public static  int[][] floodFillAlgo(int img[][],int sr, int sc,int newColor){
        int m = img.length;
        int n = img[0].length;
        int r[] = {1,-1,0,0};
        int c[] = {0,0,1,-1};
        int color = img[sr][sc];
        dfsFloodFill(img,sr,sc,newColor,color, r,c);
        return img;
    }

    public static void dfsFloodFill(int img[][],int sr,int sc,int newColor, int color, int r[],int c[]){
        img[sr][sc]=newColor;
        for(int k = 0;k<4;k++ ){
            int r1 = sr+r[k];
            int c1 = sc+c[k];
            if(r1<img.length && r1>=0 && c1<img[0].length && c1>=0 && img[r1][c1]==color && img[r1][c1]!=newColor){
                dfsFloodFill(img,r1,c1,newColor,color,r,c);
            }
        }
    }

    //We can solve the same problem using BFS also with same approach by storing parent.

    public static boolean detectCycleDfs(LinkedList<Integer> li[]){

        int vis[] = new int[li.length];

        for(int i = 0; i < li.length; i++){
            if(vis[i] == 0){
                if(dfsCycleDetectSolve(li, vis, -1, i))
                    return true;
            }
        }

        return false;
    }

    public static boolean dfsCycleDetectSolve(LinkedList<Integer> li[],
                                              int vis[],
                                              int parent,
                                              int src){

        vis[src] = 1;
        for(int ele : li[src]){

            if(vis[ele] == 0){
                if(dfsCycleDetectSolve(li, vis, src, ele))
                    return true;
            }
            else if(ele != parent){
                return true;
            }
        }

        return false;
    }

    //Detect Cycle in directed graph
    public static boolean detectCycleDirected(LinkedList<Integer> graph[]){

        int V = graph.length;
        int visited[] = new int[V];
        int pathVisited[] = new int[V];

        for(int i = 0; i < V; i++){
            if(visited[i] == 0){
                if(dfs(graph, visited, pathVisited, i))
                    return true;
            }
        }
        return false;
    }

    public static boolean dfs(LinkedList<Integer> graph[],
                              int visited[],
                              int pathVisited[],
                              int src){

        visited[src] = 1;
        pathVisited[src] = 1;

        for(int neighbor : graph[src]){

            if(visited[neighbor] == 0){
                if(dfs(graph, visited, pathVisited, neighbor))
                    return true;
            }
            else if(pathVisited[neighbor] == 1){
                return true;
            }
        }
        pathVisited[src] = 0;  // backtrack
        return false;
    }

    // Replace 0 to 1
    public static void replaceO(char[][] board){

        int m = board.length;
        int n = board[0].length;

        // Step 1: DFS from boundary O's

        for(int i = 0; i < m; i++){
            if(board[i][0] == 'O')
                dfs(board, i, 0);

            if(board[i][n-1] == 'O')
                dfs(board, i, n-1);
        }

        for(int j = 0; j < n; j++){
            if(board[0][j] == 'O')
                dfs(board, 0, j);

            if(board[m-1][j] == 'O')
                dfs(board, m-1, j);
        }

        // Step 2: Replace remaining O's
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){

                if(board[i][j] == 'O')
                    board[i][j] = 'X';

                if(board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }

    public static void dfs(char[][] board, int r, int c){

        if(r < 0 || r >= board.length ||
                c < 0 || c >= board[0].length ||
                board[r][c] != 'O')
            return;

        board[r][c] = '#';

        dfs(board, r+1, c);
        dfs(board, r-1, c);
        dfs(board, r, c+1);
        dfs(board, r, c-1);
    }


    //No of Enclave
    public static int numEnclaves(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // Step 1: Remove boundary connected land
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1)
                dfs(grid, i, 0);

            if (grid[i][n - 1] == 1)
                dfs(grid, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 1)
                dfs(grid, 0, j);

            if (grid[m - 1][j] == 1)
                dfs(grid, m - 1, j);
        }

        // Step 2: Count remaining land
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    count++;
            }
        }

        return count;
    }

    public static void dfs(int[][] grid, int r, int c) {

        if (r < 0 || r >= grid.length ||
                c < 0 || c >= grid[0].length ||
                grid[r][c] == 0)
            return;

        grid[r][c] = 0;  // mark visited

        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }


    // Count distinct island
    public static int countDistinctIslands(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1 && !visited[i][j]) {

                    ArrayList<String> shape = new ArrayList<>();
                    dfs(grid, visited, i, j, i, j, shape);

                    set.add(shape.toString());
                }
            }
        }

        return set.size();
    }

    public static void dfs(int[][] grid, boolean[][] visited,
                           int r, int c,
                           int baseR, int baseC,
                           ArrayList<String> shape) {

        int m = grid.length;
        int n = grid[0].length;

        if (r < 0 || r >= m ||
                c < 0 || c >= n ||
                grid[r][c] == 0 ||
                visited[r][c])
            return;

        visited[r][c] = true;

        // store relative position
        shape.add((r - baseR) + ":" + (c - baseC));

        dfs(grid, visited, r + 1, c, baseR, baseC, shape);
        dfs(grid, visited, r - 1, c, baseR, baseC, shape);
        dfs(grid, visited, r, c + 1, baseR, baseC, shape);
        dfs(grid, visited, r, c - 1, baseR, baseC, shape);
    }




    // Count distinct island if transformation, rotation and reflection allowed.
    public class Solution {

        public static int countDistinctIslands(int[][] grid) {

            int m = grid.length;
            int n = grid[0].length;

            boolean[][] visited = new boolean[m][n];
            HashSet<String> set = new HashSet<>();

            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){

                    if(grid[i][j] == 1 && !visited[i][j]){

                        ArrayList<int[]> island = new ArrayList<>();
                        dfs(grid, visited, i, j, island);

                        String canonical = getCanonicalForm(island);
                        set.add(canonical);
                    }
                }
            }

            return set.size();
        }

        private static void dfs(int[][] grid, boolean[][] visited,
                                int r, int c,
                                ArrayList<int[]> island){

            int m = grid.length;
            int n = grid[0].length;

            if(r < 0 || r >= m ||
                    c < 0 || c >= n ||
                    grid[r][c] == 0 ||
                    visited[r][c])
                return;

            visited[r][c] = true;
            island.add(new int[]{r, c});

            dfs(grid, visited, r+1, c, island);
            dfs(grid, visited, r-1, c, island);
            dfs(grid, visited, r, c+1, island);
            dfs(grid, visited, r, c-1, island);
        }

        private static String getCanonicalForm(ArrayList<int[]> island){

            int[][] transforms = {
                    {1,1}, {1,-1}, {-1,1}, {-1,-1}
            };

            List<String> forms = new ArrayList<>();

            for(int[] t : transforms){

                List<int[]> shape1 = new ArrayList<>();
                List<int[]> shape2 = new ArrayList<>();

                for(int[] point : island){

                    int x = point[0];
                    int y = point[1];

                    shape1.add(new int[]{ t[0]*x, t[1]*y });
                    shape2.add(new int[]{ t[0]*y, t[1]*x });
                }

                forms.add(normalize(shape1));
                forms.add(normalize(shape2));
            }

            Collections.sort(forms);
            return forms.get(0);
        }

        private static String normalize(List<int[]> shape){

            Collections.sort(shape, (a, b) ->
                    a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

            int baseX = shape.get(0)[0];
            int baseY = shape.get(0)[1];

            StringBuilder sb = new StringBuilder();

            for(int[] p : shape){
                sb.append((p[0] - baseX) + ":" + (p[1] - baseY) + ";");
            }

            return sb.toString();
        }
    }
}
