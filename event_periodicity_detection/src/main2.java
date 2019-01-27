
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        CPDA_detection cpda_detection = new CPDA_detection();
        cpda_detection.read(location);
        cpda_detection.getInitialData();
        cpda_detection.getA_locations();
        cpda_detection.getInitialGraph();
        System.out.println("events:");
        for(int i=0;i<cpda_detection.events.size();i++){
            System.out.println(cpda_detection.events.get(i));
        }
        System.out.println("initialdata:");
        for(int i=0;i<cpda_detection.initialData.size();i++){
            System.out.println(cpda_detection.initialData.get(i));
        }
        System.out.println("A_locations:");
        for(int i=0;i<cpda_detection.a_locations.size();i++){
            System.out.println(cpda_detection.a_locations.get(i));
        }
        System.out.println("Initialgraph:");
        cpda_detection.finallyGraph.show();

        Map<Integer, Integer> map = histogram_S.getneedmap(cpda_detection.initialData);
        System.out.println("lag:"+map.size());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        vector_S tttt = new vector_S(0,8);
        tttt.setSegment();
        for(int i=0;i<tttt.segment.size();i++){
            System.out.println(tttt.segment.get(i));
        }
    }

}

