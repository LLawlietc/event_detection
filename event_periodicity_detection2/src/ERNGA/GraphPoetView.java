package ERNGA;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.Transformer;

import javax.swing.*;
import java.awt.*;

/**
 * GraphPoetView
 *
 * @author cheng
 * @create 2018-12-10 17:37
 */
public class GraphPoetView {
    SparseGraph<String, String> localGraph;//JUNG
    Transformer<String, Paint> vertexPaint;//JUNG
    Transformer<String, Stroke> edgeStrokeTransformer;//JUNG
    JFrame frame;

    /**
     * 构造函数.
     *
     * @param graph : 自己建立好的graph
     */
    public GraphPoetView(Graph graph) {
        createWindow();
        createGraph(graph);
        createTransformers();

    }

    private void createTransformers() {
        // Setup up a new vertex to paint transformer...
        this.vertexPaint = new Transformer<String, Paint>() {
            public Paint transform(String s) {
                return Color.GREEN;
            }
        };
        // Set up a new stroke Transformer for the edges
        final Stroke edgeStroke = new BasicStroke();
        edgeStrokeTransformer = new Transformer<String, Stroke>() {
            public Stroke transform(String s) {
                return edgeStroke;
            }
        };

    }

    /**
     * 让graph以GUI的方式展示出来.
     */
    public void showGraph() {

        // The Layout<V, E> is parameterized by the vertex and edge types
        Layout<String, String> layout = new CircleLayout<String, String>(localGraph);
        // sets the initial size of the space
        layout.setSize(new Dimension(600, 600));
        // The BasicVisualizationServer<V,E> is parameterized by the edge types
        BasicVisualizationServer<String, String> vv =
                new BasicVisualizationServer<String, String>(layout);
        // Sets the viewing area size
        vv.setPreferredSize(new Dimension(650, 650));
        // apply transformers
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<String>());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
    }

    //用你自己的Graph来建立可以被JUNG可视化的graph
    public void createGraph(Graph graph) {
        // Graph<V, E> where V is the type of the vertices and E is the type of
        // the edges
        localGraph = new SparseGraph<String, String>();//可以可视化的graph
        // Add some vertices
        for (String v : graph.vertices) {
            localGraph.addVertex(v);
        }
        // Add some edges. default is for undirected edges.
        for (Edge edge : graph.edges) {
            //addEdge有很多的重载方式。这里的方式为addEdge(Edge的标签，Edge的起始点的标签，Edge的终点的标签,Edge的类型)
            localGraph.addEdge(edge.edge, edge.getVerticesOfEdge().get(0),
                    edge.getVerticesOfEdge().get(1), EdgeType.DIRECTED);


        }
    }

    public void createWindow() {
        frame = new JFrame("GraphPoetView");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}



