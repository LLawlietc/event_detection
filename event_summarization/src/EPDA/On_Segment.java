package EPDA;

import java.util.ArrayList;

/**
 * on_segment
 *
 * @author cheng
 * @create 2018-12-04 16:59
 */
public class On_Segment {
   private int starttime;
   private int endtime;

    public On_Segment(Integer starttime, Integer endtime){
        this.starttime = starttime;
        this.endtime = endtime;
    }
    public int getStarttime() {
        return starttime;
    }

    public void setStarttime(int starttime) {
        this.starttime = starttime;
    }

    public int getEndtime() {
        return endtime;
    }

    public void setEndtime(int endtime) {
        this.endtime = endtime;
    }
    public ArrayList<Integer> getTimeData(detection det){
        return det.timeData;
    }
    public void show(detection det){
        int start = getTimeData(det).get(starttime);
        int end = getTimeData(det).get(endtime+1);
        System.out.println("["+start+", "+end+"]");
    }
}
