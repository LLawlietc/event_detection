package CPDA;

import EPDA.event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * 每个边对应一个直方图
 *
 * @author cheng
 * @create 2018-12-06 18:17
 */
public class vector_S {
    Integer start_point;
    Integer end_point;
    Double  value;
    histogram_S histogram;
    ArrayList<event> segment;
    CPDA_detection test;
    String location = "D:\\code_house\\java\\1.txt";
    String a = "A";
    String b = "E";
    public vector_S(Integer start_point, Integer end_point) throws IOException{
        this.start_point = start_point;
        this.end_point = end_point;
        test = new CPDA_detection();
        test.start(location,a,b);
    }
    public ArrayList<event> getSegment() {
        return segment;
    }
    public Integer getSw(){
        return segment.get(segment.size()-1).getTime() - segment.get(0).getTime();
    }

    public ArrayList<event> setSegment()  {
        ArrayList<event> initial = test.initialData;
        ArrayList<event> ex_segment = new ArrayList<event>();
        Integer starttime = test.a_locations.get(start_point).getA_time();
        Integer endtime = test.a_locations.get(end_point).getA_time();
        //System.out.println("starttime"+starttime);
        //System.out.println("endtime" + endtime);
        int i=0,j=0;
        while(initial.get(i).getTime()!=starttime){
            i++;
        }
        while(initial.get(j).getTime()!=endtime){
            j++;
        }
        //System.out.println("i"+i);
       // System.out.println("j"+j);
        for(int m =i;m<=j;m++){
            ex_segment.add(initial.get(m));
        }
        this.segment = ex_segment;
        return  ex_segment;
    }

    public Integer getStart_point() {
        return start_point;
    }

    public void setStart_point(Integer start_point) {
        this.start_point = start_point;
    }

    public Integer getEnd_point() {
        return end_point;
    }

    public void setEnd_point(Integer end_point) {
        this.end_point = end_point;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public histogram_S getHistogram() {
        return histogram;
    }

    public histogram_S setHistogram() {
        histogram_S hs = new histogram_S();
        Map<Integer,Integer> mid_segment = histogram_S.getneedmap(segment);
        hs.L_C = mid_segment;
        hs.Sw = getSw();
        this.histogram = hs;
        return hs;
    }
    public histogram_S setApproximateHs(){
        return null;
    }
    public Double finallyValue(){
        this.value = histogram.encoding_length;
        return histogram.encoding_length;
    }
}
