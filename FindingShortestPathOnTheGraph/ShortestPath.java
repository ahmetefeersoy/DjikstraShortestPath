import java.util.Stack;

public class ShortestPath {
    private final Graph graph;
    protected  int dist[] ;
    protected  Edge edgeTo[] ;


    public ShortestPath(Graph graph) {
        this.graph = graph;
        dist = new int[graph.V()];
        edgeTo= new Edge[graph.V()];
    }
    public Iterable<Edge> pathTo(int v) {
        Stack<Edge> path = new Stack<>();
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[graph.getVertexIndex(e.either())]) {
            if(path.contains(e))
            break;
            path.push(e);
        }
        return path;
    }
    

    int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < graph.V(); v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

   
 
    
    void dijkstra(String source) {
        int src = graph.getVertexIndex(source);

        Boolean sptSet[] = new Boolean[graph.V()];

       
        for (int i = 0; i < graph.V(); i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
            edgeTo[i]=null;
        }

        dist[src] = 0;

        for (int count = 0; count < graph.V() - 1; count++) {
       
            int u = minDistance(dist, sptSet);

            sptSet[u] = true;

      
            for (Edge edge : graph.adj(u)) {
                int v = graph.getVertexIndex(edge.other(graph.vertexNames[u]));
                
                if (!sptSet[v] && dist[u] + edge.getDistance() < dist[v]){
                    dist[v] = dist[u] + edge.getDistance();
                    edgeTo[v]=edge;
                }   
            }
        }

       // printSolution(dist,parent);
    }
}
