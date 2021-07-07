import java.util.*;

public class BreadthFirstSearch {
    private Vertex[] shortestPath;

    public BreadthFirstSearch(Graph graph, Vertex[] path){
        LinkedList<Vertex> queue = new LinkedList<Vertex>();
        queue.add(path[0]);
        Vertex current = path[0];
        current.setDist(0);
        while(!queue.isEmpty()) {
            current = queue.poll();
            List<Edge> neighbors = current.getNeighbors();
            for(Edge edge: neighbors){
                Vertex neighbor;
                if(edge.getEndpoint1() != current){
                    neighbor = edge.getEndpoint1();
                }else{
                    neighbor = edge.getEndpoint2();
                }
                if(neighbor.getDist() > current.getDist() + 1){
                    neighbor.setDist( current.getDist() + 1);
                    neighbor.setPred(current);
                    queue.add(neighbor);
                }
            }

        }


        //sets the shortestPath
        Vertex[] tempPath = new Vertex[(int)path[1].getDist() + 1];
        current = path[1];
        for(int i = tempPath.length - 1; i > -1 ; i--){
            tempPath[i] = current;
            current = current.getPred();
        }
        shortestPath = tempPath;
    }

    public Vertex[] getPath(){
        return shortestPath;
    }
}
