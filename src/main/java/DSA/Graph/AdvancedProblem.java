package DSA.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class AdvancedProblem {

    public static void main(String[] args) {

    }

    //KosaRaju Algorithm
    public static void dfs1(int node, boolean vis[], Stack<Integer> st, ArrayList<ArrayList<Integer>> adj){

        vis[node] = true;

        for(int nbr : adj.get(node)){
            if(!vis[nbr])
                dfs1(nbr,vis,st,adj);
        }

        st.push(node);
    }

    public static ArrayList<ArrayList<Integer>> reverseGraph(int V, ArrayList<ArrayList<Integer>> adj){

        ArrayList<ArrayList<Integer>> rev = new ArrayList<>();

        for(int i=0;i<V;i++)
            rev.add(new ArrayList<>());

        for(int i=0;i<V;i++){
            for(int nbr : adj.get(i)){
                rev.get(nbr).add(i);
            }
        }

        return rev;
    }

    public static void dfs2(int node, boolean vis[], ArrayList<ArrayList<Integer>> rev){

        vis[node] = true;

        for(int nbr : rev.get(node)){
            if(!vis[nbr])
                dfs2(nbr,vis,rev);
        }
    }

    public static int kosaRaju(int V, ArrayList<ArrayList<Integer>> adj){

        boolean vis[] = new boolean[V];
        Stack<Integer> st = new Stack<>();

        // Step 1
        for(int i=0;i<V;i++){
            if(!vis[i])
                dfs1(i,vis,st,adj);
        }

        // Step 2
        ArrayList<ArrayList<Integer>> rev = reverseGraph(V,adj);

        Arrays.fill(vis,false);

        int count = 0;

        // Step 3
        while(!st.isEmpty()){

            int node = st.pop();

            if(!vis[node]){
                dfs2(node,vis,rev);
                count++;
            }
        }

        return count;
    }

    //Find bridges in graph

    public static void findBridges(int V, ArrayList<ArrayList<Integer>> adj){

        int vis[] = new int[V];
        int tin[] = new int[V];
        int low[] = new int[V];

        for(int i=0;i<V;i++){
            if(vis[i]==0){
                dfs(i,-1,vis,tin,low,adj);
            }
        }
    }

    static int timer = 1;

    public static void dfs(int node, int parent,
                           int vis[],
                           int tin[],
                           int low[],
                           ArrayList<ArrayList<Integer>> adj){

        vis[node] = 1;

        tin[node] = low[node] = timer++;

        for(int nbr : adj.get(node)){

            if(nbr == parent)
                continue;

            if(vis[nbr] == 0){

                dfs(nbr,node,vis,tin,low,adj);

                low[node] = Math.min(low[node],low[nbr]);

                if(low[nbr] > tin[node]){
                    System.out.println(node + " - " + nbr + " is a bridge");
                }
            }
            else{
                low[node] = Math.min(low[node],tin[nbr]);
            }
        }
    }

    //Articulation Point
    static int timer1 = 1;

    public static void dfs(int node, int parent,
                           int vis[],
                           int tin[],
                           int low[],
                           int mark[],
                           ArrayList<ArrayList<Integer>> adj){

        vis[node] = 1;
        tin[node] = low[node] = timer1++;

        int child = 0;

        for(int nbr : adj.get(node)){

            if(nbr == parent)
                continue;

            if(vis[nbr] == 0){

                dfs(nbr,node,vis,tin,low,mark,adj);

                low[node] = Math.min(low[node],low[nbr]);

                if(low[nbr] >= tin[node] && parent != -1){
                    mark[node] = 1;
                }

                child++;
            }
            else{

                low[node] = Math.min(low[node],tin[nbr]);
            }
        }

        if(parent == -1 && child > 1){
            mark[node] = 1;
        }
    }
    public static ArrayList<Integer> articulationPoints(int V,
                                                        ArrayList<ArrayList<Integer>> adj){

        int vis[] = new int[V];
        int tin[] = new int[V];
        int low[] = new int[V];
        int mark[] = new int[V];

        for(int i=0;i<V;i++){
            if(vis[i]==0){
                dfs(i,-1,vis,tin,low,mark,adj);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=0;i<V;i++){
            if(mark[i]==1)
                ans.add(i);
        }

        return ans;
    }

}
