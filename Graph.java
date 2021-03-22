import javax.swing.*;
import java.util.*;

public class Graph extends JFrame {

    private MyGraphics graphics;
    private Vertex vertices[];
    private Edge edges[];

    public Graph(int numOfVertices) {
        vertexGeneration(numOfVertices);
        EdgeGeneration();
        graphics = new MyGraphics(vertices, edges);
        this.setSize(510, 510);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(graphics);
        this.setVisible(true);
    }

    public void vertexGeneration(int numOfVertices) {
        vertices = new Vertex[numOfVertices];
        Random rand = new Random();
        for (int i = 0; i < numOfVertices; i++) {
            int x = rand.nextInt(480) + 10;
            int y = rand.nextInt(480) + 10;
            vertices[i] = new Vertex(x, y);
        }
    }

    //This function makes sure that the graph has the most edges it could have without two edges ever crossing each other.
    //It does this by sorting every edge by distance then adding the shortest edges to the graph if they dont intersect any
    //previous edges.
    public void EdgeGeneration(){
        int edgeNum = -1;
        double[][] possibleEdges = new double[vertices.length * (vertices.length-1)][3];
        for(int vertexOne=0; vertexOne<vertices.length; vertexOne++){
            for(int vertexTwo=0; vertexTwo<vertices.length; vertexTwo++){
                if(vertexOne != vertexTwo) {     //vertices[vertexOne].x
                    double distance = (Math.pow((vertices[vertexOne].getX() - vertices[vertexTwo].getX()), 2) + Math.pow(vertices[vertexOne].getY() - vertices[vertexTwo].getY(), 2));
                    double[] edge = {vertexOne, vertexTwo, distance};
                    edgeNum++;
                    insertionSort(edge, possibleEdges, edgeNum);
                }
            }
        }
        List<Edge> listOfEdges = new ArrayList<Edge>();
        for(int i=0; i<possibleEdges.length; i += 2){
            boolean possibleEdge = true;
            double x1 = vertices[(int)possibleEdges[i][0]].getX();
            double x2 = vertices[(int)possibleEdges[i][1]].getX();
            double y1 = vertices[(int)possibleEdges[i][0]].getY();
            double y2 = vertices[(int)possibleEdges[i][1]].getY();
            double slope = (y2-y1) / (x2-x1);
            possibleEdges[i][2] = slope;
            for(int j = 0; j < listOfEdges.size(); j++){
                int existingx1 = listOfEdges.get(j).getEndpoint1().getX();
                int existingx2 = listOfEdges.get(j).getEndpoint2().getX();
                double b1 = y1 - slope * x1;
                double b2 = (double)listOfEdges.get(j).getEndpoint1().getY() - listOfEdges.get(j).getSlope() * (double)listOfEdges.get(j).getEndpoint1().getX();
                double xIntercept = (b2 - b1)/(slope - listOfEdges.get(j).getSlope());
                if(xIntercept <= Math.min(x1, x2) || xIntercept >= Math.max(x1, x2)){
                    ;
                }else if(xIntercept <= Math.min(existingx2, existingx1) || xIntercept >= Math.max(existingx2, existingx1)){
                    ;
                }else if(vertices[(int)possibleEdges[i][0]] == listOfEdges.get(j).getEndpoint1() ||                     //These next two if statements just make sure the two edges don't share a vertex.
                        vertices[(int)possibleEdges[i][0]] == listOfEdges.get(j).getEndpoint2()){
                    ;
                }else if(vertices[(int)possibleEdges[i][1]] == listOfEdges.get(j).getEndpoint1() ||
                        vertices[(int)possibleEdges[i][1]] == listOfEdges.get(j).getEndpoint2()){
                    ;
                }else{
                    possibleEdge = false;
                }
            }
            if(possibleEdge){
                Edge newEdge = new Edge(vertices[(int)possibleEdges[i][0]], vertices[(int)possibleEdges[i][1]]);
                listOfEdges.add(newEdge);
            }
        }
        edges = listOfEdges.toArray(new Edge[listOfEdges.size()]);
    }


    //This gets the leftmost and rightmost vertex to make the paths more interesting
    public Vertex[] getStartAndEnd(){
        Vertex[] pathEndpoints = new Vertex[2];
        Vertex start, end;
        int i = 0;
        start = vertices[i];
        end = vertices[i];
        i++;
        while(i < vertices.length){
            if(start.getX() > vertices[i].getX()){
                start = vertices[i];
            }else if(end.getX() < vertices[i].getX()){
                end = vertices[i];
            }i++;
        }
        pathEndpoints[0] = start;
        pathEndpoints[1] = end;
        return pathEndpoints;
    }

    public void setWeights(){
        ;
    }

    public void insertionSort(double[] newEdge, double[][] possibleEdges, int edgeNum){
        int iter = edgeNum-1;
        while(iter >=0 && possibleEdges[iter][2] > newEdge[2]){
            possibleEdges[iter+1] = possibleEdges[iter];
            iter--;
        }possibleEdges[iter+1] = newEdge;
    }

    public MyGraphics graphics(){
        return graphics;
    }

}
