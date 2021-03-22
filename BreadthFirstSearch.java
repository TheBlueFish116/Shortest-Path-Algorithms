import java.util.*;

public class BreadthFirstSearch {
    private Vertex[] shortestPath;

    public BreadthFirstSearch(Graph graph, Vertex[] path){
        Vertex endpoint = path[1];
        LinkedList<Vertex> queue = new LinkedList<Vertex>();
        queue.add(path[0]);
        Vertex current = path[0];
        current.setDist(0);
        while(!queue.isEmpty()) {
            current = queue.poll();
            List<Vertex> neighbors = current.getNeighbors();
            for(int i = 0; i < neighbors.size(); i ++){
                if(neighbors.get(i).getDist() > current.getDist() + 1){
                    neighbors.get(i).setDist( current.getDist() + 1);
                    neighbors.get(i).setPred(current);
                    queue.add(neighbors.get(i));
                }
            }

        }


        //set the shortestPath
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
