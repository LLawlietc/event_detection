import java.util.ArrayList;

/**
 * periode_model
 *
 * @author cheng
 * @create 2018-12-04 16:55
 */
public class periods_model {
   private String event;
   private int p;
   private int time_tolerance;
   private ArrayList<On_Segment> segments;

   public periods_model(String event, int p, int time_tolerance, ArrayList<On_Segment> segments){
       this.event = event;
       this.p = p;
       this.time_tolerance = time_tolerance;
       this.segments = segments;
   }
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getTime_tolerance() {
        return time_tolerance;
    }

    public void setTime_tolerance(int time_tolerance) {
        this.time_tolerance = time_tolerance;
    }

    public ArrayList<On_Segment> getSegments() {
        return segments;
    }

    public ArrayList<Integer> getTimeData(detection det){
       return det.timeData;
    }
    public void addSegments(On_Segment segment) {
        segments.add(segment);
    }
    public void show(detection det){
        System.out.println("event = "+event+", period = "+p+", time_tolerance = "+time_tolerance);
        System.out.println("segments: ");
        for(int i=0;i<segments.size();i++) {
            segments.get(i).show(det);
        }
    }
}
