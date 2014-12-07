package ch9;

import com.sun.deploy.net.proxy.StaticProxyManager;

import java.util.Stack;

/**
 * Created by Jun on 12/6/2014.
 *
 * PROBLEM: 9.7 given a graph (2-d array) and a point, fill the graph
 *
 * HINT: like in graphics class, stack all nodes/runs needed to paint.
 *
 *
 */
public class PaintFill {
    public enum Color  {RED, GREEN, BLUE};

    public static void main(String[] args) {
        Color[][] graph = {{Color.RED, Color.RED, Color.RED},
                            {Color.RED, Color.GREEN, Color.RED},
                            {Color.RED, Color.RED, Color.RED}};
        PaintFill painter = new PaintFill();
        Point test = new Point(0,1);
        painter.paint(graph, new Point(0,0), graph[0][0], Color.BLUE);
        painter.drawGraph(graph);

    }

    public void drawGraph(Color[][] graph){
        for(int i = 0; i < graph.length; i ++){
            drawLine(graph[i]);
        }
    }

    public void drawLine(Color[] graph){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < graph.length; i ++){
            sb.append(graph[i] + " ");
        }
        System.out.println(sb.toString());
    }

    void paint(Color[][] graph, Point p, Color oldColor, Color newColor){
        Stack<Point> runs = new Stack<Point>();
        runs.push(p);
        while (!runs.isEmpty()){
            scan(graph, runs, oldColor, newColor);
        }

    }

    private void scan(Color[][] graph, Stack runs, Color oldColor, Color newColor){
        if(graph == null || runs == null || runs.isEmpty()) return;
        Point focus = (Point)runs.pop();
        System.out.println("scaned point " + focus);
        int left = focus.x;
        int right = focus.x;  // these two used to store the location and length of this run, so as to stack upper and lower run

        //scan this line
        if(focus.x != 0){//scan left
            for(int i = focus.x - 1; i >=0; i --){
                if(graph[focus.y][i] == oldColor){
                    graph[focus.y][i] = newColor;
                    left = i;
                }else {
                    break;
                }
            }
        }
        if(focus.x != graph[0].length-1){//scan right
            for(int i = focus.x + 1; i < graph[0].length; i ++){
                if(graph[focus.y] [i]== oldColor){
                    graph[focus.y][i] = newColor;
                    right = i;
                }else {
                    break;
                }
            }
        }

        //put the upper line and lower line into stack
        boolean flagUpper = true;
        boolean flagLower = true;
        for(int i = right; i >= left; i --){
            if(focus.y != 0){ // not the first line -- has an upper line
                if(flagUpper && graph[focus.y - 1][i]== oldColor){
                    runs.push(new Point(i,focus.y - 1));
                    flagUpper = false;
                }else if(!flagUpper && graph[focus.y - 1][i]!=oldColor){
                    flagUpper = true;
                }
            }

            if(focus.y != graph.length-1){ // not the last line -- has an lower line
                if(flagLower && graph[focus.y +1][i] == oldColor){
                    runs.push(new Point(i, focus.y + 1));
                    flagLower = false;
                }else if(!flagLower && graph[focus.y +1][i] != oldColor){
                    flagLower = true;
                }
            }
        }
        graph[focus.y][focus.x] = newColor;
    }

//    private void scanLeft(int )

}
     class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public String toString(){
            return "y: " + y + " x: " + x;
        }
    }
