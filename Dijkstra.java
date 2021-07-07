import javax.swing.text.AbstractDocument;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dijkstra {
    private Vertex[] shortestPath;


    //Dijkstras algorithm is built off of BFS and the contructor function implements BFS to visit each node.
    public Dijkstra(Graph graph, Vertex[] path){
        graph.setWeights();
        LinkedList<Vertex> queue = new LinkedList<Vertex>();
        queue.add(path[0]);
        Vertex current = path[0];
        current.setDist(0);
        while(!queue.isEmpty()) {
            current = queue.poll();
            current.visit();
            List<Edge> neighbors = current.getNeighbors();

            for(Edge edge: neighbors){
                Vertex neighbor;
                if(edge.getEndpoint1() != current){
                    neighbor = edge.getEndpoint1();
                }else{
                    neighbor = edge.getEndpoint2();
                }
                if(neighbor.getDist() > current.getDist() + edge.getWeight()){
                    neighbor.setDist(current.getDist()+edge.getWeight());
                    neighbor.setPred(current);
                }
            }

            for(Edge edge: neighbors){
                Vertex neighbor;
                if(edge.getEndpoint1() != current){
                    neighbor = edge.getEndpoint1();
                }else{
                    neighbor = edge.getEndpoint2();
                }
                //Change this
                if(neighbor.visited() != true){
                    queue.add(neighbor);
                }
            }

        }
        List<Vertex> tempPath = new ArrayList<Vertex>();

        current = path[1];

        while(current.getPred() != null){
            tempPath.add(0, current);
            current = current.getPred();
        }tempPath.add(0, current);

        shortestPath = tempPath.toArray(new Vertex[tempPath.size()]);
    }

    public Vertex[] getPath(){
        return shortestPath;
    }

}
