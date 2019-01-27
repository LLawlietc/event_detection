package ERNGA;

import CPDA.correlation_model;
import CPDA.on_segment;
import EPDA.On_Segment;
import EPDA.periods_model;

import java.util.ArrayList;

/**
 * ernga
 *
 * @author cheng
 * @create 2018-12-10 15:26
 */
public class main3 {
    public static void main(String[] args){
        int totallength = 100;
        ArrayList<On_Segment> p1s = new ArrayList<On_Segment>();
        p1s.add(new On_Segment(1,30));
        p1s.add(new On_Segment(32,40));
        periods_model p1 = new periods_model("A",5,20,p1s);
        ArrayList<On_Segment> p2s = new ArrayList<On_Segment>();
        p2s.add(new On_Segment(21,30));
        p2s.add(new On_Segment(41,50));
        periods_model p2 = new periods_model("B",6,30,p2s);
        ArrayList<on_segment> c1s = new ArrayList<on_segment>();
        c1s.add(new on_segment(3,6));
        c1s.add(new on_segment(6,40));
        c1s.add(new on_segment(40,50));
        correlation_model c1 = new correlation_model("A","C",3.0,30,c1s);
        ArrayList<on_segment> c2s = new ArrayList<on_segment>();
        c2s.add(new on_segment(3,5));
        c2s.add(new on_segment(5,20));
        c2s.add(new on_segment(20,60));
        correlation_model c2 = new correlation_model("A","B",4.0,40,c2s);

        Graph graph = new Graph();
        graph.addVertices(p1);
        graph.addVertices(p2);
        graph.addVertices(c1);
        graph.addVertices(c2);

        Edge e1 = new Edge(totallength);
        e1.addEdge(p1);
        Edge e2 = new Edge(totallength);
        e2.addEdge(p2);
        Edge e3 = new Edge(totallength);
        e3.addEdge(c1);
        Edge e4 = new Edge(totallength);
        e4.addEdge(c2);

        graph.addEdges(e1);
        graph.addEdges(e2);
        graph.addEdges(e3);
        graph.addEdges(e4);


        //传递一个自己建立的graph
        GraphPoetView a = new GraphPoetView(graph);
        a.showGraph();

    }
}
