import java.util.*;

public class Vertex {
    private int x;
    private int y;
    private List<Vertex> neighbors;
    private double dist = Double.POSITIVE_INFINITY;
    private Vertex pred;
    private int id;


    public Vertex(int x, int y, int id){
        this.x = x;
        this.y = y;
        this.id = id;
        neighbors = new ArrayList<Vertex>();
    }

    public void addNeighbor(Vertex newNeighbor){
        neighbors.add(newNeighbor);
    }

    public List<Vertex> getNeighbors(){
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

    public int getY(){
        return y;
    }

    public int getId(){
        return id;
    }
}
