import java.util.ArrayList;
import java.util.List;

public class Vertex {
     public Integer value;
     public List<Edge> edges;

     public int count;

     public Vertex(Integer value){
         edges=new ArrayList<Edge>();
         this.value=value;

     }

     public void addEdge(Vertex endVertex, Integer weight){
         this.edges.add(new Edge(this,endVertex, weight));
     }


     public void removeEdge(Vertex vertexToBeRemoved){
         this.edges.removeIf(edge -> edge.getEndVertex().equals(vertexToBeRemoved));
     }


     public void removeVertex(Integer weight){
         edges.removeIf(edge-> edge.getWeight()!=null && edge.getWeight()==weight);
     }


}
