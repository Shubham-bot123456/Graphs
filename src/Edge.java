public class Edge {

    private Integer weight;
    private Vertex startVertex;
    private Vertex endVertex;

    public Edge(Vertex startVertex, Vertex endVertex, Integer weight) {
        this.startVertex=startVertex;
        this.endVertex=endVertex;
        this.weight=weight;
    }
    public Vertex getEndVertex(){
        return this.endVertex;
    }

    public Integer getWeight(){
        return this.weight;
    }
}
