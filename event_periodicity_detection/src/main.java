import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * main
 *
 * @author cheng
 * @create 2018-12-04 10:26
 */
public class main {
    public static void main(String[] args) throws IOException {
        detection test = new detection(500);
        String location = "D:\\code_house\\java\\1.txt";
        LinkedHashMap<Integer,ArrayList<Integer>> map = test.start(location);
        ArrayList<periods_model> models = new ArrayList<periods_model>();
        for(Integer key : map.keySet()){
            ArrayList<Integer> set = map.get(key);
            ArrayList<On_Segment> onSegments = extract(set);
            periods_model model = new periods_model("A",test.arrivalTime.get(key), test.time_tolerance, onSegments);
            models.add(model);
        }
        for(int i=0;i<models.size();i++){
            models.get(i).show(test);
        }
    }

    public static ArrayList<On_Segment> extract(ArrayList<Integer> list){

        ArrayList<On_Segment> timelist = new ArrayList<On_Segment>();

        int biaoji = 0;     //0表示在寻找下一状态       1表示在当前状态寻找下一元素
        int start = 0;
        int end = 0;

        for(int i=0;i<list.size()-1;i++){
            if(biaoji==0){

                if(list.get(i)==list.get(i+1)-1){    //至少两个连续

                    biaoji = 1;
                    start = i;

                    if(i==list.size()-2){

                        end = i+1;
                        On_Segment segment = new On_Segment(list.get(start),list.get(end));
                        timelist .add(segment);

                    }
                }
            }else if(biaoji==1){

                if(list.get(i)==list.get(i+1)-1){

                    if(i==list.size()-2){
                        end = i+1;
                        On_Segment segment = new On_Segment(list.get(start),list.get(end));
                        timelist .add(segment);
                    }
                }else{
                    end = i;
                    On_Segment segment = new On_Segment(list.get(start),list.get(end));
                    timelist .add(segment);
                    biaoji = 0;
                }
            }
        }
        return timelist;
    }
}
