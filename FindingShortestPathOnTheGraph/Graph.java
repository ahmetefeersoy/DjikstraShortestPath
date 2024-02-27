import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Graph
{
   private final int V; 
   public final Bag<Edge>[] adj;
   public final String[] vertexNames;
   private List<Edge> edgelist = new ArrayList<>();


   public Graph(int V)
   {
      this.V = V;
      this.vertexNames = new String[V];

      adj = (Bag<Edge>[]) new Bag[V];
      for (int v = 0; v < V; v++)
         adj[v] = new Bag<Edge>();
         
   }
    public int getIndex(String vertexName) {
        int existingIndex = getVertexIndex(vertexName);
        if (existingIndex != -1) {
            return existingIndex;
        }
    
        for (int i = 0; i < V; i++) {
            if (vertexNames[i] == null) {
                vertexNames[i] = vertexName;
                return i;
            }
        }
    
        return -1; 
    }
    
        //gives city's index number

    public int getVertexIndex(String vertexName) {
        for (int i = 0; i < V; i++) {
            if (vertexNames[i] != null && Objects.equals(vertexNames[i], vertexName)) {
                return i;
            }
        }
        return -1;
    }

    public void addEdge(Edge e)
    {
       String v = e.either(), w = e.other(v);
       int vIndex = getIndex(v);
       int wIndex = getIndex(w);
       adj[vIndex].add(e);
       adj[wIndex].add(e);
       edgelist.add(e);
    }
 
public Iterable<Edge> edges()
   {  return edgelist;  }

public int V (){
    return this.V;
}

   public Iterable<Edge> adj(int v)
   {  return adj[v];  }
}