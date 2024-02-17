public class Main {

  public static void main(String[] args) {
    Vertex one = new Vertex(1);
    Vertex two = new Vertex(2);
    Vertex three = new Vertex(3);
    Vertex four = new Vertex(4);
    Graphs graph = new Graphs(true, true);
    graph.addVertexObject(one);
    graph.addVertexObject(two);
    graph.addVertexObject(three);
    graph.addVertexObject(four);
    graph.addEdge(one, two, 10);
    graph.addEdge(two, three, 20);
    graph.addEdge(three, one, 30);
    graph.addEdge(one, four, 40);
    System.out.println(
      "the largest component is " + graph.largestComponentByRecussiveWay()
    );
    System.out.println(
      "the shortest path  between is " + graph.shortestPath(one, four)
    );
  }
}
