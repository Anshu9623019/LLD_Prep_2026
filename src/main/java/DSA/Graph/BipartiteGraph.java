package DSA.Graph;

import java.util.*;

public class BipartiteGraph {

    public static void main(String[] args) {

    }

    //Bipertite Graph : BFS
    public static boolean isBipartite(int[][] graph) {

        int n = graph.length;
        int[] color = new int[n];

        Arrays.fill(color, -1);  // -1 = not colored

        for(int i = 0; i < n; i++){

            if(color[i] == -1){

                if(!bfsCheck(graph, i, color))
                    return false;
            }
        }

        return true;
    }

    private static boolean bfsCheck(int[][] graph, int start, int[] color){

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        color[start] = 0;

        while(!q.isEmpty()){

            int node = q.poll();

            for(int neighbor : graph[node]){

                if(color[neighbor] == -1){

                    color[neighbor] = 1 - color[node];
                    q.add(neighbor);

                } else if(color[neighbor] == color[node]){

                    return false;
                }
            }
        }

        return true;
    }


    //Bipertite : DFS
    private static boolean dfsBipartite(int[][] graph, int node, int col, int[] color){

        color[node] = col;

        for(int neighbor : graph[node]){

            if(color[neighbor] == -1){

                if(!dfsBipartite(graph, neighbor, 1-col, color))
                    return false;

            } else if(color[neighbor] == col){

                return false;
            }
        }

        return true;
    }

    public static List<Integer> eventualSafeNodes(int[][] graph) {

        int n = graph.length;

        boolean[] visited = new boolean[n];
        boolean[] pathVis = new boolean[n];
        boolean[] safe = new boolean[n];

        for(int i = 0; i < n; i++){
            if(!visited[i]){
                dfsSafeState(graph, i, visited, pathVis, safe);
            }
        }

        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < n; i++){
            if(safe[i])
                result.add(i);
        }

        return result;
    }

    private static boolean dfsSafeState(int[][] graph, int node,
                                        boolean[] visited,
                                        boolean[] pathVis,
                                        boolean[] safe){

        visited[node] = true;
        pathVis[node] = true;

        for(int neighbor : graph[node]){

            if(!visited[neighbor]){
                if(dfsSafeState(graph, neighbor, visited, pathVis, safe))
                    return true;
            }
            else if(pathVis[neighbor]){
                return true;   // cycle detected
            }
        }

        pathVis[node] = false;
        safe[node] = true;  // if no cycle found
        return false;
    }

    //Alien Dictionary left


}
