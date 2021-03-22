public class Edge {
    private Vertex endpoint1;
    private Vertex endpoint2;
    private double slope;
    private int weight;
    private boolean hasWeights = false;

    public Edge(Vertex vertex1, Vertex vertex2){
        endpoint1 = vertex1;
        endpoint2 = vertex2;
        slope = (double)(endpoint2.getY() - endpoint1.getY())/(double)(endpoint2.getX() - endpoint1.getX());
        endpoint1.addNeighbor(endpoint2);
        endpoint2.addNeighbor(endpoint1);
    }

    public Vertex getEndpoint1(){
        return endpoint1;
    }

    public Vertex getEndpoint2(){
        return endpoint2;
    }

    public double getSlope(){
        return slope;
    }

    public int getWeight(){
        return weight;
    }
}
