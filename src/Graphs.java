import java.util.*;

public class Graphs {
    private List<Vertex> vertexList;
    private boolean isDirected;
    private boolean isWeighted;

    public List<Vertex> getVertexList(){
        return this.vertexList;
    }
    public Graphs(boolean isDirected, boolean isWeighted) {
        this.vertexList = new ArrayList<Vertex>();
        this.isDirected = isDirected;
        this.isWeighted = isWeighted;
    }


    public void addVertex(Integer value) {
        Vertex vertex = new Vertex(value);
        this.vertexList.add(vertex);
    }

    public void addVertexObject(Vertex vertex) {
        this.vertexList.add(vertex);
    }

    public void addEdge(Vertex startVertex, Vertex endVertex, Integer weight) {
        if (!isDirected) {
            endVertex.addEdge(startVertex, weight);
        }
        startVertex.addEdge(endVertex, weight);
    }


    public void depthFirstSearch(Vertex start) {
        Stack<Vertex> stack = new Stack<>();
        stack.add(start);
        while (!stack.isEmpty()) {
            start = stack.pop();
            System.out.println(start.value);
            for (Edge edge : start.edges) {
                stack.add(edge.getEndVertex());
            }
        }
    }

    public void breathFirstSearch(Vertex start) {
        Queue<Vertex> queue = new PriorityQueue<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            start = queue.poll();
            System.out.println(start.value);
            for (Edge edge : start.edges) {
                queue.add(edge.getEndVertex());
            }
        }
    }

    // by recurssive way.
    public boolean isCyclic(Vertex vertex) {
        Stack<Vertex> stack = new Stack<>();
        stack.add(vertex);
        boolean isFirstCycle = true;
        while (!stack.isEmpty()) {
            Vertex current = stack.pop();
            if (vertex.value == current.value && !isFirstCycle) {
                return true;
            }
            for (Edge edge : current.edges) {
                stack.add(edge.getEndVertex());
            }
            isFirstCycle = !isFirstCycle;
        }
        return false;
    }

    public boolean isCyclicRec(Vertex vertex) {
        for (Edge edge : vertex.edges) {
            if (recurssion(vertex, edge.getEndVertex())) return true;
        }
        return false;
    }

    private boolean recurssion(Vertex target, Vertex current) {
        if (current == null) {
            return false;
        } else if (target.value == current.value) {
            return true;
        }
        for (Edge edge : current.edges) {
            if (recurssion(target, edge.getEndVertex())) return true;
        }
        return false;
    }

    public boolean hasPathRec(Vertex start, Vertex end) {
        if (start == null) {
            return false;
        } else if (start.value == end.value) {
            return true;
        }
        for (Edge edge : start.edges) {
            if (hasPath(edge.getEndVertex(), end)) return true;
        }
        return false;
    }


    public boolean hasPath(Vertex start, Vertex end) {
        Stack<Vertex> stack = new Stack<>();
        stack.add(start);
        while (!stack.isEmpty()) {
            start = stack.pop();
            if (start.value == end.value) {
                return true;
            }
            for (Edge edge : start.edges) {
                stack.add(edge.getEndVertex());
            }
        }
        return false;
    }


    public boolean hasPathForUndirectedGraphs(Vertex start, Vertex destination, Set<Integer> dp) {
        dp.add(start.value);
        if (start == null) {
            return false;
        } else if (start.value == destination.value) {
            return true;
        }

        for (Edge edge : start.edges) {
            if (dp.contains(edge.getEndVertex().value)) continue;
            if (hasPathForUndirectedGraphs(edge.getEndVertex(), destination, dp)) return true;
            dp.add(edge.getEndVertex().value);
        }
        return false;
    }


    public int connectedComponents() {
        int count = 0;
        Set<Integer> hashSet = new HashSet<>();
        for (Vertex vertex : this.vertexList) {
            if (hashSet.contains(vertex.value))
                continue;
            Stack<Vertex> stack = new Stack<>();
            stack.add(vertex);
            Vertex currentVertex = null;
            while (!stack.isEmpty()) {
                currentVertex = stack.pop();
                hashSet.add(currentVertex.value);
                for (Edge edge : currentVertex.edges) {
                    if (hashSet.contains(edge.getEndVertex().value))
                        continue;
                    stack.add(edge.getEndVertex());
                }
            }
            count++;
        }
        return count;
    }

    public int largestComponent() {
        int largestComponent = 0;
        Set<Integer> hashSet = new HashSet<>();
        for (Vertex vertex : this.vertexList) {
            int currentCount = 0;
            if (hashSet.contains(vertex.value))
                continue;
            Stack<Vertex> stack = new Stack<>();
            stack.add(vertex);
            Vertex currentVertex = null;
            while (!stack.isEmpty()) {
                currentVertex = stack.pop();
                if (!hashSet.contains(currentVertex.value))
                    currentCount++;
                hashSet.add(currentVertex.value);
                for (Edge edge : currentVertex.edges) {
                    if (hashSet.contains(edge.getEndVertex().value))
                        continue;
                    stack.add(edge.getEndVertex());
                }
            }
            if (currentCount > largestComponent)
                largestComponent = currentCount;
        }
        return largestComponent;
    }

    // semi recurssive way
    public int largestComponentByRecussiveWay() {
        Set<Integer> hashSet = new HashSet<>();
        int largest = 0;
        for (Vertex vertex : vertexList) {
            if (!hashSet.contains(vertex.value)) {
                int count = recLC(hashSet, vertex);
                if(count> largest) largest=count;
            }
        }
        return largest;
    }

    private int recLC(Set<Integer> refSet, Vertex currentVertex) {
        if (currentVertex == null || refSet.contains(currentVertex.value)) {
            return 0;
        }
        refSet.add(currentVertex.value);
        int count = 1;
        for (Edge edge : currentVertex.edges) {
            if (refSet.contains(edge.getEndVertex().value)) continue;
            // rec call
            count += recLC(refSet, edge.getEndVertex());
        }
        return count;
    }
     public int shortestPath(Vertex start, Vertex  desitination) {
         int shortestPath = 0;
         Set<Integer> hashSet = new HashSet<>();
         Queue<Vertex> queue = new PriorityQueue<>(new Comparator<Vertex>() {
             @Override
             public int compare(Vertex o1, Vertex o2) {
                 return o1.value - o2.value;
             }
         });
         queue.add(start);
         while (!queue.isEmpty()) {
             Vertex currentVertex = queue.poll();
             if (hashSet.contains(currentVertex.value)) continue;
             if (desitination.value == currentVertex.value) {
                 if (shortestPath == 0 || (shortestPath > currentVertex.count))
                     shortestPath = currentVertex.count;
             }
             hashSet.add(currentVertex.value);
             for (Edge edge : currentVertex.edges) {
                 if (hashSet.contains(edge.getEndVertex())) continue;
                 edge.getEndVertex().count = currentVertex.count + 1;
                 queue.add(edge.getEndVertex());
             }
         }
         return shortestPath;
     }

     // think so this algo for directed graphs only.
    public void dijkstraAlgo(Vertex start) {
        Queue<Vertex> queue = new PriorityQueue<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
               return o1.value-o2.value;
            }
        });
        queue.add(start);
        while(!queue.isEmpty()){
            Vertex currentVertex=queue.poll();
            for(Edge edge : currentVertex.edges) {
                if(edge.getEndVertex().count==0 || (currentVertex.count + edge.getWeight()) < edge.getEndVertex().count){
                    edge.getEndVertex().count= currentVertex.count + edge.getWeight();
                }
                queue.add(edge.getEndVertex());
            }
        }
    }




}





