import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

public class DijkstraSP {
    protected Edge[] edgeTo;
    protected double[] distTo;
    private PriorityQueue<Node> pq;
    protected Graph g;

    public DijkstraSP(Graph G, String s) {
        this.g=G;
        int V = G.V();
        edgeTo = new Edge[V];
        distTo = new double[V];
        pq = new PriorityQueue<>(V, (a, b) -> Double.compare(a.weight, b.weight));

        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[G.getVertexIndex(s)] = 0.0;
        pq.add(new Node(G.getVertexIndex(s), 0.0));

        while (!pq.isEmpty()) {
            int v = pq.poll().vertex;
            for (Edge e : G.adj(v)) {
                relax(G,e);
            }
        }
    }

    private void relax(Graph G,Edge e) {
        int w = G.getVertexIndex(e.other(e.either()));
        int v= G.getVertexIndex(e.either());
        if (distTo[w] > distTo[v] + e.getDistance()) {
            distTo[w] = distTo[v] + e.getDistance();
            edgeTo[w] = e;

            Node node = new Node(w, distTo[w]);
            if (pq.contains(node)) {
                pq.remove(node);
            }
            pq.add(node);
        }
    }
     public double distTo(int v)  {  
        return distTo[v];  }  
        
     public Iterable<Edge> pathTo(int v)  {   
        Stack<Edge> path = new Stack<Edge>();   
    for (Edge e = edgeTo[v]; e != null; e = edgeTo[g.getVertexIndex(e.either())])    
        path.push(e);    
         return path;  } 


    private static class Node {
        int vertex;
        double weight;

        Node(int vertex, double weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}
