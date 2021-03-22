import java.awt.*;
import javax.swing.*;


public class MyGraphics extends JPanel{

    private Vertex vertices[];
    private Edge edges[];
    private Vertex path[];

    public MyGraphics(Vertex[] listOfVertices, Edge[] listOfEdges){
        vertices = listOfVertices;
        edges = listOfEdges;
        if(edges == null){
            System.out.println("HI");
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for(int i=0; i<edges.length; i++){
            g2d.setColor(Color.BLACK);
            g2d.drawLine(edges[i].getEndpoint1().getX(),edges[i].getEndpoint1().getY(), edges[i].getEndpoint2().getX(), edges[i].getEndpoint2().getY());
        }
        for(int i = 0; i < vertices.length; i++){
            g2d.setColor(Color.BLACK);
            if(path != null) {
                for (int j = 0; j < path.length; j++) {
                    if(j == 0 && path[j] == vertices[i]){
                        g2d.setColor(Color.GREEN);
                    }else if(j == path.length-1 && path[j] == vertices[i]){
                        g2d.setColor(Color.RED);

                    }else if (path[j] == vertices[i]) {
                        g2d.setColor(Color.BLUE);
                    }
                }
            }
            g2d.fillOval(vertices[i].getX()-10,vertices[i].getY()-10,20,20);
            g2d.setColor(Color.WHITE);
            g2d.fillOval(vertices[i].getX()-5,vertices[i].getY()-5,10,10);
            g2d.setColor(Color.BLUE);
            String id = ""+i+"";
            g2d.drawString(id,vertices[i].getX()-10,vertices[i].getY()-10);
        }
    }

    public void setPath(Vertex pathList[]){
        path = pathList;
    }
}
