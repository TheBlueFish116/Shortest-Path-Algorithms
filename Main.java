import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int numOfVertices = 20;
        Graph graph = new Graph(numOfVertices);


        Vertex[] path = graph.getStartAndEnd();

        //This is where you run the algorithms, comment out the algorithms you don't want to use.
//        BreadthFirstSearch BFS = new BreadthFirstSearch(graph, path);
        Dijkstra SearchAlgorithm = new Dijkstra(graph, path);


        path = SearchAlgorithm.getPath();
        graph.graphics().setPath(path);
    }
}
