public class Edge implements Comparable<Edge>
{
   private final String x, y;

   
   private final int distance;

   public int getDistance() {
      return distance;
   }

   public Edge(String x, String y,int distance)   {
      this.x = x;
      this.y = y;
      this.distance =distance;
   }

   public String either()  {  return x;  }

   public String other(String vertex) {

      if (vertex.equals(x)) return y;
      else return x; 
   }

   public int compareTo(Edge that) {
      if      (this.distance < that.distance) return -1;
      else if (this.distance > that.distance) return +1;
      else                                    return  0;
   }


}