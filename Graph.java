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
        this.setSize(1000, 1000);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(graphics);
        this.setVisible(true);
    }

    public void vertexGeneration(int numOfVertices) {
        vertices = new Vertex[numOfVertices];
        Random rand = new Random();
        int x = rand.nextInt(480) + 10;
        int y = rand.nextInt(480) + 10;
        vertices[0] = new Vertex(x, y, 0);
        int vertexNum = 1;
        for (int i = 1; i < numOfVertices; i++) {
            Boolean tooClose = false;
            x = rand.nextInt(480) + 10;
            y = rand.nextInt(480) + 10;
            for (int j = 0; j < vertexNum; j++){
                double distance = Math.sqrt(Math.pow((vertices[j].getX() - x), 2) + Math.pow(vertices[j].getY() - y, 2));
                if(distance < 70){
                    i -= 1;
                    tooClose = true;
                    break;
                }
            }if(!tooClose){
                vertices[i] = new Vertex(x, y, i);
                vertexNum += 1;
            }
        }
                                                                                                                                            //delete this V
//        vertices[0] = new Vertex(100, 160, 0);
//        vertices[1] = new Vertex(100, 220, 1);
//        vertices[2] = new Vertex(50, 180, 2);
//        vertices[3] = new Vertex(135, 180, 3);
    }

    /*This function makes sure that the graph has the most edges it could without two edges ever crossing each other.
    It does this by sorting every edge by distance then adding the shortest edges to the graph if they dont intersect any
    previous edges.*/
    public void EdgeGeneration(){
        int edgeNum = -1;
        double[][] possibleEdges = new double[vertices.length * (vertices.length-1)][3];
        for(int vertexOne=0; vertexOne<vertices.length; vertexOne++){
            for(int vertexTwo=0; vertexTwo<vertices.length; vertexTwo++){
                if(vertexOne != vertexTwo) {     //vertices[vertexOne].x
                    double distance = Math.sqrt(Math.pow((vertices[vertexOne].getX() - vertices[vertexTwo].getX()), 2) + Math.pow(vertices[vertexOne].getY() - vertices[vertexTwo].getY(), 2));
                    double[] edge = {vertexOne, vertexTwo, distance};
                    edgeNum++;
                    insertionSort(edge, possibleEdges, edgeNum);
                }
            }
        }
        fixArray(possibleEdges);
        List<Edge> listOfEdges = new ArrayList<Edge>();
        for(int i=0; i<possibleEdges.length; i += 2){
            boolean possibleEdge = true;
            double x1 = vertices[(int)possibleEdges[i][0]].getX();
            double x2 = vertices[(int)possibleEdges[i][1]].getX();
            double y1 = vertices[(int)possibleEdges[i][0]].getY();
            double y2 = vertices[(int)possibleEdges[i][1]].getY();
            Edge temp = new Edge(vertices[(int)possibleEdges[i][0]], vertices[(int)possibleEdges[i][1]]);
            for(Edge edge: listOfEdges){
                double intersection[] = edgeIntersection(edge, temp);
                int existingx1 = edge.getEndpoint1().getX();
                int existingx2 = edge.getEndpoint2().getX();
                int existingy1 = edge.getEndpoint1().getY();
                int existingy2 = edge.getEndpoint2().getY();
                if(intersection[0] < Math.min(x1, x2) || intersection[0] > Math.max(x1, x2))
                    ;
                else if(intersection[1] < Math.min(y1, y2) || intersection[1] > Math.max(y1, y2))
                    ;
                else if(intersection[0] < Math.min(existingx1, existingx2) || intersection[0] > Math.max(existingx1, existingx2))
                    ;
                else if(intersection[1] < Math.min(existingy1, existingy2) || intersection[1] > Math.max(existingy1, existingy2))
                    ;
                else if (temp.getEndpoint1() == edge.getEndpoint1() ||
                        temp.getEndpoint1() == edge.getEndpoint2())
                    ;
                else if (temp.getEndpoint2() == edge.getEndpoint1() ||
                        temp.getEndpoint2() == edge.getEndpoint2())
                    ;
                else {
                    possibleEdge = false;
                }
            }
            if(possibleEdge){
                listOfEdges.add(temp);
                temp.addNeighbors();
            }
        }
        edges = listOfEdges.toArray(new Edge[listOfEdges.size()]);
    }


    //This gets the two vertices that are the farthest apart to make the graph more interesting
    public Vertex[] getStartAndEnd(){
        Vertex[] pathEndpoints = new Vertex[2];
        Vertex start, end;
//        int i = 0;
//        start = vertices[i];
//        end = vertices[i];
//        i++;
//        while(i < vertices.length){
//            if(start.getX() > vertices[i].getX()){
//                start = vertices[i];
//            }else if(end.getX() < vertices[i].getX()){
//                end = vertices[i];
//            }i++;
//        }
        pathEndpoints[0] = start;
        pathEndpoints[1] = end;
        return pathEndpoints;
    }

    public void setWeights(){
        for(Edge edge: edges){
            int distance = (int)Math.sqrt(Math.pow((edge.getEndpoint1().getX() - edge.getEndpoint2().getX()), 2) + Math.pow(edge.getEndpoint1().getY() - edge.getEndpoint2().getY(), 2));
            distance = distance/2;
            edge.setWeight(distance);
        }
    }

    public void setDirections(Vertex[] pathEndpoints){
        Vertex[] visited = new Vertex[vertices.length];
        LinkedList<Vertex> queue = new LinkedList<Vertex>();
        queue.add(pathEndpoints[0]);
        Vertex current = pathEndpoints[0];
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
                boolean nVisited = false;
                for(Vertex vert : visited){
                    if(neighbor == vert){
                        nVisited = true;
                    }
                }
                if(nVisited == false){
                    edge.setDirection(neighbor);
                    queue.add(neighbor);
                }
            }

        }
    }

    public void insertionSort(double[] newEdge, double[][] possibleEdges, int edgeNum){
        int iter = edgeNum-1;
        while(iter >=0 && possibleEdges[iter][2] > newEdge[2]){
            possibleEdges[iter+1] = possibleEdges[iter];
            iter--;
        }possibleEdges[iter+1] = newEdge;
    }


    public void fixArray(double[][] array){
        for(int i = 0; i < array.length; i+=2){
            if(array[i][0] != array[i+1][1] || array[i][1] != array[i+1][0]){
                for(int j = i+1; j < array.length; j++){
                    if(array[j][0] == array[i][1] && array[j][1] == array[i][0]){
                        double tempArray[] = array[i+1];
                        array[i+1] = array[j];
                        array[j] = tempArray;
                        break;
                    }
                }
            }
        }
    }


    //finds reduced echelon form of a matrix to see where two edges intersect
    public double[] edgeIntersection(Edge edge1, Edge edge2){
        int lead = 0;
        int columnCount = 3;
        int rowcount = 2;
        double matrix[][] = {{edge1.getA(), edge1.getB(), edge1.getC()}, {edge2.getA(), edge2.getB(), edge2.getC()}};

        for(int r = 0; r < 2; r++){
            if(columnCount <= lead){
                break;
            }
            int i = r;
            while(matrix[i][lead] == 0){
                i++;
                if (rowcount == i) {
                    i = r;
                    lead++;
                    if(columnCount == lead){
                        double sauce[] = {0.0, 0.0};
                        return(sauce);
                    }
                }
            }
            double[] tempRow = matrix[r];
            matrix[r] = matrix[i];
            matrix[i] = tempRow;
            if(matrix[r][lead] != 0){
                double val = matrix[r][lead];
                for(int j = 0; j < columnCount; j++){
                    matrix[r][j] = matrix[r][j] / val;
                }
            }
            for(i = 0; i < rowcount; i++){
                if(i != r){
                    for(int j = columnCount - 1; j > -1; j--) {
                        double subtractedNum = matrix[i][lead] * matrix[r][j];
                        matrix[i][j] -= subtractedNum;
                    }
                }
            }lead++;
        }
        double[] intersection = new double[2];
        intersection[0] =-1 * matrix[0][columnCount-1];
        intersection[1] =-1 * matrix[1][columnCount-1];
        return intersection;
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public MyGraphics graphics(){
        return graphics;
    }
}
