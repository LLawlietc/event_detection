package CPDA;

import CPDA.CPDA_detection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * 测试2
 *
 * @author cheng
 * @create 2018-12-06 15:33
 */
public class main2  {
    public static void main(String[] args) throws IOException {
        String location = "D:\\code_house\\java\\1.txt";
        String a = "A";
        String b = "E";
        Integer time_tolerance = 500;
        CPDA_detection cpda_detection = new CPDA_detection();
        cpda_detection.start(location,a,b);

        System.out.println("initialdata:");
        for(int i=0;i<cpda_detection.initialData.size();i++){
            System.out.println(cpda_detection.initialData.get(i));
        }
        System.out.println("A_locations:");
        for(int i=0;i<cpda_detection.a_locations.size();i++){
            System.out.println(cpda_detection.a_locations.get(i));
        }
      // System.out.println("size:" + cpda_detection.a_locations.size());

        int vector_num = cpda_detection.finallyGraph.nodeNum;
        for(int i=0;i<vector_num;i++){
            for(int j=i+1;j<vector_num;j++){
                vector_S temp = new vector_S(i,j);
                temp.setSegment();
                temp.setHistogram();
                temp.histogram.start();
                temp.finallyValue();
                cpda_detection.finallyGraph.addEdge(temp.start_point,temp.end_point,temp.value);
            }
        }
        //取周期p
        vector_S fin = new vector_S(0,cpda_detection.a_locations.size()-1);
        fin.setSegment();
        fin.setHistogram();
        fin.histogram.start();
        fin.finallyValue();
        Double p = fin.histogram.p;
        System.out.println("finally graph:");
        cpda_detection.finallyGraph.show();

        ShortestPath sp = ShortestPath.Dijkstra(cpda_detection.finallyGraph,0);
//        System.out.println("-------------");
//        for(int i=0;i<sp.preNodes.length;i++){
//            System.out.println(sp.preNodes[i]);
//        }
        correlation_model cm = new correlation_model();
        System.out.println("event: " + a + "->" + b + "period: "+p+"time_tolerance: "+time_tolerance);
        System.out.println();
        // sp.analyse();
        cm.setStartevent(a);
        cm.setEndevent(b);
        cm.setP(p);
        cm.setTime_tolerance(time_tolerance);

        ArrayList<Integer> path = new ArrayList<Integer>();
        path.add(cpda_detection.initialData.get(cpda_detection.initialData.size()-1).getTime());
        if (sp.distances[sp.distances.length-1] < Graph.INF) {
            int j = sp.distances.length-1;
            while (j != 0) {
                path.add(cpda_detection.a_locations.get(j).getA_time());
                j = sp.preNodes[j];
            }
            path.add(cpda_detection.a_locations.get(0).getA_time());
        }
        for(int i=path.size()-1;i>0;i--){
            System.out.println("["+path.get(i)+", "+path.get(i-1)+"]");
            on_segment os = new on_segment(path.get(i),path.get(i-1));
            cm.addSegments(os);
        }



}


}

