public class Edge {
    private Vertex endpoints[];
    private Vertex endpoint1;
    private Vertex endpoint2;
    private double A;
    private double B;
    private double C;

    private int weight;
    private boolean hasWeights = false;

    public Edge(Vertex vertex1, Vertex vertex2){
        endpoint1 = vertex1;
        endpoint2 = vertex2;

        A = vertex1.getY() - vertex2.getY();
        B = -1 * (vertex1.getX() - vertex2.getX());
        C = -1 * (A * vertex1.getX() + B * vertex1.getY());
    }

    public void addNeighbors(){
        endpoint1.addNeighbor(endpoint2);
        endpoint2.addNeighbor(endpoint1);
    }

    public Vertex getEndpoint1(){
        return endpoint1;
    }

    public Vertex getEndpoint2(){
        return endpoint2;
    }

    public double getA(){
        return A;
    }

    public double getB(){
        return B;
    }

    public double getC(){
        return C;
    }

    public int getWeight(){
        return weight;
    }
}
