import java.util.ArrayList;
import java.util.List;

public class BellmanFord {
    private Vertex[] shortestPath;

    public BellmanFord(Graph graph, Vertex[] path){
        graph.setWeights();
        path[0].setDist(0);
        boolean relaxedAnEdge = true;
        while(relaxedAnEdge) {
            relaxedAnEdge = false;
            for (Edge edge : graph.getEdges()) {
                if(relaxEdge(edge)){
                    relaxedAnEdge = true;
                }
            }
        }

        //the algorithm is done the rest of the code just sets the path so it can be returned.
        List<Vertex> tempPath = new ArrayList<Vertex>();

        Vertex vert = path[1];

        while(vert.getPred() != null){
            tempPath.add(0, vert);
            vert = vert.getPred();
        }tempPath.add(0, vert);

        shortestPath = tempPath.toArray(new Vertex[tempPath.size()]);




    }

    private boolean relaxEdge(Edge edge){
        Vertex end1 = edge.getEndpoint1();
        Vertex end2 = edge.getEndpoint2();
        if(end1.getDist() > end2.getDist() + edge.getWeight()){
            double newDist = end2.getDist() + edge.getWeight();
            end1.setDist(newDist);
            end1.setPred(end2);
            return true;
        }else if(end2.getDist() > end1.getDist() + edge.getWeight()){
            double newDist = end1.getDist() + edge.getWeight();
            end2.setDist(newDist);
            end2.setPred(end1);
            return true;
        }else{
            return false;
        }
    }

    public Vertex[] getPath(){
        return shortestPath;
    }
}
