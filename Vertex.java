import java.util.*;

public class Vertex {
    private int x;
    private int y;
    private List<Edge> neighbors;
    private double dist = Double.POSITIVE_INFINITY;
    private Vertex pred;
    private int id;
    private boolean wasVisited = false;


    public Vertex(int x, int y, int id){
        this.x = x;
        this.y = y;
        this.id = id;
        neighbors = new ArrayList<Edge>();
    }

    public void addNeighbor(Edge newNeighbor){
        neighbors.add(newNeighbor);
    }

    public List<Edge> getNeighbors(){
        return neighbors;
    }

    public double getDist(){
        return dist;
    }

    public void setDist(double x){
        dist = x;
    }

    public Vertex getPred(){
        return pred;
    }

    public void setPred(Vertex predecessor){
        pred = predecessor;
    }

    public int getX(){
        return x;
    }

    public boolean visited(){
        return wasVisited;
    }

    public void visit(){
        wasVisited = true;
    }

    public int getY(){
        return y;
    }

    public int getId(){
        return id;
    }
}
