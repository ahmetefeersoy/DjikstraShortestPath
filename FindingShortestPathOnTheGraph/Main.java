import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main{

public static void main(String[] args) throws IOException {
   List<String> wantToVisitCity=new ArrayList<String>();
   List<Edge> listOfPath=new ArrayList<>();
      List<String> listOfPathtemp=new ArrayList<>();

   int totalDistance=0;
Scanner sc = new Scanner(System.in);
String filename = sc.nextLine().trim();

Graph cityGraph = readCityFile(filename);

String source = sc.nextLine();
wantToVisitCity.add(source);

String destination = sc.nextLine();

int numberOfCityYouWantToVisit= sc.nextInt();
if(numberOfCityYouWantToVisit!=0){
    for(int i =0;i<numberOfCityYouWantToVisit;i++){
 String city = sc.next();
 wantToVisitCity.add(city);
    }
}

  ShortestPath sp = new ShortestPath(cityGraph);
boolean check = false;
  for(int i=0;i<wantToVisitCity.size();i++){
  sp.dijkstra(wantToVisitCity.get(i));
  if(i==wantToVisitCity.size()-1){
  wantToVisitCity.add(destination);
    check=true;
  }
  totalDistance=totalDistance+sp.dist[cityGraph.getVertexIndex(wantToVisitCity.get(i+1))];
   listOfPath.addAll((Collection<? extends Edge>) sp.pathTo(cityGraph.getVertexIndex(wantToVisitCity.get(i+1)))) ;
  if(check)
  wantToVisitCity.remove(wantToVisitCity.size()-1);
  }
  for(Edge m:listOfPath){

  if(!listOfPathtemp.contains(m.either())&&!listOfPathtemp.contains(m.other(m.either()))){
  listOfPathtemp.add(m.either());
  listOfPathtemp.add(m.other(m.either()));
  }
  if(!listOfPathtemp.contains(m.either())&&listOfPathtemp.contains(m.other(m.either()))){
listOfPathtemp.add(m.either());
  }
  if(listOfPathtemp.contains(m.either())&&!listOfPathtemp.contains(m.other(m.either()))){
  listOfPathtemp.add(m.other(m.either()));
  }
}
System.out.println("Routes are:");
int count=0;
for(String s:listOfPathtemp){
System.out.print(s);
if(count!=listOfPathtemp.size()-1)
System.out.print("-");
count++;
}
System.out.println();
System.out.println("Length of route is: "+totalDistance);
}



 private static Graph readCityFile(String filename) throws IOException{
        Set<String> listOfCities = new HashSet<>();
        List<Edge> listOfEdges=new ArrayList<>();        
        String filepath1 = "/Users/ahmetefeersoy/Desktop/VSC/CMPE224HW3Q2/"+filename;
        String filepath = filename;

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath1))) {
    
            String line;

            while ((line = reader.readLine()) != null) {
                String[] graphEdges = line.split(",");
                listOfCities.add(graphEdges[0].trim());
                listOfCities.add(graphEdges[1].trim());
                Edge edge = new Edge(graphEdges[0].trim(),graphEdges[1].trim(),Integer.parseInt(graphEdges[2]));
                listOfEdges.add(edge);
            } 
            Graph graph = new Graph(listOfCities.size());
            for (int i = 0; i < listOfEdges.size(); i++) {
               graph.addEdge(listOfEdges.get(i));
            }


        return graph;
        } catch (IOException e) {
            throw e;
        }
             

    }
    

}