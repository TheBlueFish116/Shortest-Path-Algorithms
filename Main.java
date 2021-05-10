import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int numOfVertices = 20;
        Graph graph = new Graph(numOfVertices);


        Vertex[] path = graph.getStartAndEnd();

        //This is where you run the algorithms.
        BreadthFirstSearch BFS = new BreadthFirstSearch(graph, path);
        path = BFS.getPath();



        graph.graphics().setPath(path);
    }
}
