import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
    histogram_S approximate_histogram;
    ArrayList<event> segment;
    CPDA_detection test;
    String location = "D:\\code_house\\java\\1.txt";
    public vector_S(Integer start_point, Integer end_point) throws IOException{
        this.start_point = start_point;
        this.end_point = end_point;
        test = new CPDA_detection();
        test.start(location);
    }
    public ArrayList<event> getSegment() {
        return segment;
    }

    public ArrayList<event> setSegment()  {
        ArrayList<event> initial = test.initialData;
        ArrayList<event> ex_segment = new ArrayList<event>();
        Integer starttime = test.a_locations.get(start_point).getA_time();
        Integer endtime = test.a_locations.get(end_point).getA_time();
        System.out.println("starttime"+starttime);
        System.out.println("endtime" + endtime);
        int i=0,j=0;
        while(initial.get(i).getTime()!=starttime){
            i++;
        }
        while(initial.get(j).getTime()!=endtime){
            j++;
        }
        System.out.println("i"+i);
        System.out.println("j"+j);
        for(int m =i;m<=j;m++){
            ex_segment.add(initial.get(m));
        }
        this.segment = ex_segment;
        return  ex_segment;
    }

    public histogram_S getApproximate_histogram() {
        return approximate_histogram;
    }

    public void setApproximate_histogram(histogram_S approximate_histogram) {
        this.approximate_histogram = approximate_histogram;
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

    public void setHistogram(histogram_S histogram) {
        this.histogram = histogram;
    }
}
