package ERNGA;

import CPDA.correlation_model;
import EPDA.periods_model;

import java.util.HashSet;
import java.util.Set;

/**
 * tu
 *
 * @author cheng
 * @create 2018-12-10 15:53
 */
public class Graph {
    public HashSet<String> vertices = new HashSet<String>();
    public HashSet<Edge> edges = new HashSet<Edge>();

    public void addVertices(periods_model pmodel){
        String point = pmodel.getEvent();
        vertices.add(point);
    }
    public void addVertices(correlation_model cmodel){
        String point1 = cmodel.getStartevent();
        String point2 = cmodel.getEndevent();
        vertices.add(point1);
        vertices.add(point2);
    }
    public void addEdges(Edge edge){
        edges.add(edge);
    }


}
